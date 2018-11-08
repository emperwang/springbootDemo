package com.wk.rabbit.demo.direct.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    /**
     * 因为是direct模式  而queue是一对一模式  所以定义一个queue时需要指定一个key
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue("direct");
    }
}
