package com.wk.controller.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.message.Message;
import com.wk.message.auth.AuthRequest;
import com.wk.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@Slf4j
@Controller
@ServerEndpoint("/")
public class WebSocketServer implements InitializingBean, ApplicationContextAware {
    // 消息类型 与 messageHandler 的映射
    private static final Map<String, com.wk.handler.MessageHandler> handlers = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        log.info("[onOpen][session {} 接入]",session);
        // 解析accessToken
        final Map<String, List<String>> parameterMap = session.getRequestParameterMap();
        final List<String> tokenvalues = parameterMap.get("accessToken");
        String token = !CollectionUtils.isEmpty(tokenvalues)?tokenvalues.get(0):null;
        log.info("[onOpen][接收到的token:{}]", token);
        // 创建AuthRequest
        final AuthRequest authRequest = new AuthRequest().setAccessToken(token);
        // 获得消息处理器
        final com.wk.handler.MessageHandler<AuthRequest> messageHandler = handlers.get(AuthRequest.TYPE);
        // 处理消息
        if (messageHandler == null){
            log.error("[onOpen][认证消息类型, 不存在消息处理器]");
            return;
        }
        messageHandler.execute(session,authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message){
        log.info("[onMessage][session {} 接收到一条消息:{}]",session, message);
        try {
            // 获得消息类型
            final JSONObject object = JSON.parseObject(message);
            final String msgType = object.getString("type");
            // 获得消息处理器
            final com.wk.handler.MessageHandler messageHandler = handlers.get(msgType);
            if (messageHandler == null){
                log.error("[onMessage][消息类型 {} 不存在消息处理器]", msgType);
                return;
            }
            // 解析消息
            final Class<? extends Message> messageClass = this.getMessageClass(messageHandler);

            // 处理消息
            final Message messageObj = JSON.parseObject(object.getString("body"), messageClass);
            messageHandler.execute(session, messageObj);
        }catch (Exception e){
            log.error("[onMessage][出现异常: {}]", e);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason){
        log.info("[onClose][session {} 连接关闭. 关闭原因是:{}", session, closeReason);
        WebSocketUtil.removeSession(session);
    }
    @OnError
    public void onError(Session session, Throwable throwable){
        log.info("[onError][session:{} 发生异常:{}]", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过 ApplicationContext 获得所有 MessageHandler bean
        applicationContext.getBeansOfType(com.wk.handler.MessageHandler.class)
                .values().forEach( handler -> handlers.put(handler.getType(), handler));
        log.info("[afterPropertiesSet][消息处理器数量:{}]", handlers.size());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Class<? extends Message> getMessageClass(com.wk.handler.MessageHandler handler){
        // 获得bean对应的class类型, 有可能被aop代理过
        final Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);

        // 获得接口的 type数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)){
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)){
            // 遍历interfaces 数组
            for (Type type : interfaces) {
                if (type instanceof ParameterizedType){
                    // 要求 type 是泛型参数
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler接口
                    if (Objects.equals(parameterizedType.getRawType(),com.wk.handler.MessageHandler.class)){
                        final Type[] typeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(typeArguments) && typeArguments.length >0){
                            return (Class<Message>) typeArguments[0];
                        }else{
                            throw new IllegalStateException(String.format("类型(%s) 获取不到消息类型",handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获取不到消息类型",handler));
    }
}
