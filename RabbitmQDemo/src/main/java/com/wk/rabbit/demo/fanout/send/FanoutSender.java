package com.wk.rabbit.demo.fanout.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate template;

    public void sendMsg(){
        template.convertAndSend("fanoutExchange","fanoutA"," :to fanoutA");
        //template.convertAndSend("fanoutExchange","fanoutB"," :to fanoutB");
        //template.convertAndSend("fanoutExchange","fanoutC"," :to fanoutC");
    }
}
