package com.wk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ImmutableMessageChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    private static Logger log = LoggerFactory.getLogger(WebSocketConfig.class);
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    // 注册stomp协议的节点，并映射指定的url
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // server端的连接点
        registry.addEndpoint("/endpointStomp")
                .setAllowedOrigins("*")  // 允许所有的连接
                .withSockJS();          // 使用sockJS
    }
    // 配置消息代理
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 设置代理broker
        registry.enableSimpleBroker("/topic","/user");
        //registry.setUserDestinationPrefix("/user"); // 设置用户的前缀

        // 为所有的server端的目的地添加前缀,如 这里的@MessageMapping("/welcome"),访问时使用 /app/welcome
        //registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     *  拦截器的添加
     *  stomp发送数据时，分为：命令段, header 段, data 段
     *  此处的操作就是当命令时connect时,对header中的token进行了过滤
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ImmutableMessageChannelInterceptor(){
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    String token = accessor.getNativeHeader("token").get(0);
                    if (token.equalsIgnoreCase("aaa")){
                        log.info("token is valid");
                        return message;
                    }else{
                        log.info("token is invalid");
                        return null;
                    }
                }
                return message;
            }
        });
    }
}
