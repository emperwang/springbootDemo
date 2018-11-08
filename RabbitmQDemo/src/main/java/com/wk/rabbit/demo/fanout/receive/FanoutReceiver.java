package com.wk.rabbit.demo.fanout.receive;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

/**
 * fanout 模式  会把信息广播到所有的queue中
 */
@Component
public class FanoutReceiver {

    @RabbitListener(queues = {"fanoutA"})
    public void processA(String str){
        System.out.println("ReceiveA :"+str);
    }

    @RabbitListener(queues = {"fanoutB"})
    public void processB(String str){
        System.out.println("ReceiveB :"+str);
    }

    @RabbitListener(queues = {"fanoutC"})
    public void processC(String str){
        System.out.println("ReceiveC :"+str);
    }


    @RabbitListeners(value = {
            @RabbitListener(queues = "fanoutA"),
            @RabbitListener(queues = "fanoutB"),
            @RabbitListener(queues = "fanoutC")})
    public void processAll(String str){

        System.out.println("Receive All mag is==="+str);
    }
}
