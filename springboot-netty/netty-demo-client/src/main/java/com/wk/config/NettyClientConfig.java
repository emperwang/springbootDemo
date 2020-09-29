package com.wk.config;

import com.wk.dispatcher.MessageDispatcher;
import com.wk.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyClientConfig {
    @Bean
    public MessageDispatcher messageDispatcher(){
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer(){
        return new MessageHandlerContainer();
    }
}
