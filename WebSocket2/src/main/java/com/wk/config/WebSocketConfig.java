package com.wk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ImmutableMessageChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author wk
 * 2019/09/09
 */

@Configuration
// 注解开启使用STOMP协议来传输基于代理(message broker)的消息，这时控制器支持使用@MessageMapping
// 使用起来类似@RequestMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    private static Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }

    /**
     *  注册STOMP协议的节点(Endpoint)，并映射指定的url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册一个STOMP的endpoint，并指定使用SockJS协议
        registry.addEndpoint("/endpointStomp")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     *  过滤器
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ImmutableMessageChannelInterceptor(){
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                // 1.判断是否是首次连接请求
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getNativeHeader("token").get(0);

                    if (token.equals("aaa")){
                        log.info("login success");
                        return message;
                    }else {
                        log.info("login failed");
                        return null;
                    }
                }
                return message;
            }
        });
    }
}
