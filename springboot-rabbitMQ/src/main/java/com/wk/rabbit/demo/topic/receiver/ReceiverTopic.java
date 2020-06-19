package com.wk.rabbit.demo.topic.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

/**
 * 注意rabbitMQ中的消息是消费完就没有了
 */
@Component
public class ReceiverTopic {

    @RabbitListener(queues = {"topic.message"})
    public void receiverMessageQueue(String str){

        System.out.println("receive from message is "+str);
    }

    @RabbitListener(queues = {"topic.msg"})
    public void receiveMsgQueue(String str){

        System.out.println("receive from msg is "+str);
    }

    /**
     *  此时能同时接收到两个queue中的内容
     * @param str
     */
    @RabbitListeners(value = {
            @RabbitListener(queues = {"topic.message"}),
            @RabbitListener(queues = {"topic.msg"})
    })
    public void receiveAllMsgQueue(String str){

        System.out.println("receive All Msg is "+str);
    }
}
