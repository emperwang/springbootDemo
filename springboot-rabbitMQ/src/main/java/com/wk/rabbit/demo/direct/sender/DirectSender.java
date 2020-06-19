package com.wk.rabbit.demo.direct.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DirectSender {

    /**
     * 这是springboot集成的发送模板函数
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    //@Scheduled(fixedRate = 1000)
    public void send(){
        amqpTemplate.convertAndSend("direct","this is direct model");
    }
}
