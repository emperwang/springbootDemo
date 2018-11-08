package com.wk.rabbit.demo.topic.sender;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate template;

    /**
     * 发送消息
     * 第一个参数: 交换机名称  也就是配置文件中创建的交换机的名称
     * 第二个参数: key值
     * 第三个参数: 发送的消息
     */
    //@Scheduled(fixedRate = 1000)
    public void sendMsg(){
        template.convertAndSend("topicExchange","topic.message","send to message queue");
        template.convertAndSend("topicExchange","topic.msg","send to msg queue");
    }
}
