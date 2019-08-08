package com.wk.web.controller;

import com.wk.util.KafkaProducerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class kafkaController {
    private static Logger logger = LoggerFactory.getLogger(kafkaController.class);

    @Autowired
    private KafkaProducerUtil kafkaProducer;

    @GetMapping("sendmsg")
    public String sendmsg(){
        logger.info("get request.....");
        kafkaProducer.sendMsg("test","test","this is test2");
        return "success";
    }
}
