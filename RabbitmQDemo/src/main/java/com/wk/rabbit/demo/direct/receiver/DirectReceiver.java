package com.wk.rabbit.demo.direct.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitListener(queues = {"direct"})
    public void receiver(String str){
        System.out.println("Received msg is "+str);
    }
}
