package com.wk.rabbit.demo.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这是Topic模式下的 配置
 */
@Configuration
public class TopicConfig {

    /**
     * 使用topic.message创建一个队列
     * @return
     */
    @Bean(name = "message")
    public Queue queueMessage(){
        return new Queue("topic.message");
    }

    /**
     * 使用topic.msg创建一个队列
     * @return
     */
    @Bean(name = "msg")
    public Queue queueMsg(){
        return new Queue("topic.msg");
    }

    /**
     * 创建一个交换机
     */

    @Bean
    public TopicExchange exchange(){

        return new TopicExchange("topicExchange");
    }

    /**
     * 把指定的队列绑定到交换机上 ,并指定了特定的topic-key
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage,
                                          TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 把topic.msg 队列绑定到交换机上
     * @param queueMsg
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingExchangeMsg(@Qualifier("msg") Queue queueMsg,
                                      TopicExchange exchange){
        return BindingBuilder.bind(queueMsg).to(exchange).with("topic.msg");
    }

}
