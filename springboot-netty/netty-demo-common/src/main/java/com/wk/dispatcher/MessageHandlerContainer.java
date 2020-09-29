package com.wk.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class MessageHandlerContainer implements InitializingBean, ApplicationContextAware {
    /**
     * 消息类型与 messageHandler 的映射
     */
    private final Map<String, MessageHandler> handlers = new HashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 通过ApplicationContext 获得所有 MessageHandler bean
        final Map<String, MessageHandler> hands = applicationContext.getBeansOfType(MessageHandler.class);
        hands.forEach((str, messageHandler) -> handlers.put(messageHandler.getType(), messageHandler));
        log.info("[afterPropertiesSet][消息处理器数量 :{}]", hands.size());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    MessageHandler getMessageHandler(String type){
        final MessageHandler handler = handlers.get(type);
        if (handler == null){
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", type));
        }
        return handler;
    }
    /**
     *  获得MessageHandler 处理的消息类
     * @param handler 处理器
     * @return  消息类
     */
    public static Class<? extends Message> getMessageClass(MessageHandler handler){
        // 获得bean 对应的class 类名. 因为有可能被aop代理过
        final Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);

        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        // 此处 以父类接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)){
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }

        if (Objects.nonNull(interfaces)){
            for (Type type : interfaces) {
                // 要求type 是泛型类型
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)){
                        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0){
                            return (Class<Message>) actualTypeArguments[0];
                        }else{
                            throw new IllegalStateException(String.format("类型(%s) 获取不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获取不到消息类型", handler));
    }


}










