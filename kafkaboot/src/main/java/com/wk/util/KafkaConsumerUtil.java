package com.wk.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerUtil {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerUtil.class);
    @KafkaListener(topics = "test")
    public void listenTestTopic(ConsumerRecord<String,String> record){
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        logger.info("receive msg from {},key is:{},value is:{}",topic,key,value);
    }

}
