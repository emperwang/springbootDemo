package com.wk.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wk.handler.MessageHandler;
import com.wk.message.Message;
import com.wk.message.auth.AuthRequest;
import com.wk.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: wk
 * @Date: 2020/9/30 15:29
 * @Description
 */
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler implements InitializingBean, ApplicationContextAware {

    private final Map<String, MessageHandler> handlers = new HashMap<>();

    private ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(handler -> {
                    handlers.put(handler.getType(),handler);
                });
        log.info("[afterPropertiesSet][消息处理器数量: {}]", handlers.size());
    }

    private Class<? extends Message> getMessageClass(MessageHandler handler){
        final Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);

        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();

        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && superclass != null){
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }

        if (Objects.nonNull(interfaces)){
            for (Type type : interfaces) {
                if (type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType)type;

                    if (Objects.equals(parameterizedType.getRawType(),MessageHandler.class)){
                        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0){
                            return (Class<Message>) actualTypeArguments[0];
                        }else {
                            throw new IllegalArgumentException(String.format("类型(%s) 获取不到消息类型",handler));
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException(String.format("类型(%s) 获取不到消息类型",handler));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[afterConnectionEstablished][session {} 接入]", session);

        // 解析accessToken
        String accessToken = (String)session.getAttributes().get("accessToken");
        // 创建AuthRequest 消息类型
        final AuthRequest authRequest = new AuthRequest().setAccessToken(accessToken);
        // 获取消息处理器
        final MessageHandler handler = handlers.get(AuthRequest.TYPE);
        if (handler == null){
            log.error("[afterConnectionEstablished][认证消息类型, 不存在消息处理器]");
            return;
        }
        // 处理消息
        handler.execute(session, authRequest);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("[handleMessage][session {} 接收到一条消息:{}]", session);
        try{
            // 获取消息类型
            final JSONObject jsonObject = JSON.parseObject(message.getPayload());
            final String msgtype = jsonObject.getString("type");
            // 获取消息处理器
            final MessageHandler handler = handlers.get(msgtype);
            if (handler == null){
                log.error("[handleTextMessage][消息类型 {} 不存在消息处理器]", msgtype);
                return;
            }
            // 解析消息
            final Class<? extends Message> messageClass = this.getMessageClass(handler);
            // 处理消息
            final Message messageObj = JSON.parseObject(jsonObject.getString("body"), messageClass);
            handler.execute(session, messageObj);
        }catch (Exception e){
            log.info("[handleTextMessage][session {} message 发生异常]", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("[afterConnectionClosed][session:{} 连接关闭. 关闭原因是:{}]", session,status);

        WebSocketUtil.removeSession(session);
    }

    @Override   // 对应error事件
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("[handleTransportError][session:{} 发生异常:{}]",session,exception);
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        log.info("[handlePongMessage][message:{}]", message);
        super.handlePongMessage(session, message);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
