package com.wk;

import com.wk.dispatcher.Message;
import com.wk.dispatcher.MessageHandler;
import com.wk.messagehandler.heartbeat.HeartbeatResponseHandler;
import org.junit.Test;
import org.springframework.aop.framework.AopProxyUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class getMessageClass {

    @Test
    public void test1(){
        final HeartbeatResponseHandler handler = new HeartbeatResponseHandler();
        final Class<? extends Message> aClass = getMessageClass(handler);
        System.out.println(aClass.getName());
    }


    public Class<? extends Message> getMessageClass(MessageHandler handler){
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
