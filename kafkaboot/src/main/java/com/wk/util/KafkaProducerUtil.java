package com.wk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerUtil {
    private static Logger logger = LoggerFactory.getLogger(KafkaProducerUtil.class);
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void sendMsg(String topic,String key,String value){
        logger.info("start send msg,msg is :{},key is:{},topic is:{}",value,key,topic);
        kafkaTemplate.send(topic,key,value);
    }
}
