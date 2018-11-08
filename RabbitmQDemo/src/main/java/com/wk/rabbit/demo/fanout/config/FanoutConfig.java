package com.wk.rabbit.demo.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout广播模式的配置
 */
@Configuration
public class FanoutConfig {

    /**
     * 队列A
     * @return
     */
    @Bean(name = "AMessage")
    public Queue AMessageQueue(){

        return new Queue("fanoutA");
    }

    @Bean(name = "BMessage")
    public Queue BMessage(){
        return new Queue("fanoutB");
    }

    @Bean(name = "CMessage")
    public Queue CMessage(){
        return new Queue("fanoutC");
    }

    /**
     * fanout 接换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 绑定队列A 到fanout交换机
     * @param aqueue 队列a
     * @param exchange fanout交换机
     * @return
     */
    @Bean
    public Binding bindingExchangeA(@Qualifier("AMessage") Queue aqueue,
                                    FanoutExchange exchange){
        return BindingBuilder.bind(aqueue).to(exchange);
    }

    //绑定队列B 到fanout交换机
    @Bean
    public Binding bindingExchangeB(@Qualifier("BMessage") Queue bqueue,
                                    FanoutExchange exchange){
        return BindingBuilder.bind(bqueue).to(exchange);
    }
    //绑定队列c 到fanout交换机
    @Bean
    public Binding bindingExchangeC(@Qualifier("CMessage") Queue bqueue,
                                    FanoutExchange exchange){
        return BindingBuilder.bind(bqueue).to(exchange);
    }
}
