package com.wk.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class KafkaConsumerUtil {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerUtil.class);

    ExecutorService executorService = new ThreadPoolExecutor(10,50,
            60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.CallerRunsPolicy());

    public KafkaConsumerUtil(){
        for(int i=0;i<10;i++){
            executorService.submit(this.new processRecord());
        }
    }


    @KafkaListener(topics = "test")
    public void listenTestTopic(List<ConsumerRecord<String,String>> records){
        for (ConsumerRecord<String, String> record : records) {
            String topic = record.topic();
            String key = record.key();
            String value = record.value();
            logger.info("listenTestTopic receive msg from {},key is:{},value is:{}",topic,key,value);
        }
    }

    @KafkaListener(topics = "test1")
    public void listenTestSubmitOffset(List<ConsumerRecord<String,String>> records, Acknowledgment ack){
        ack.acknowledge();  // 提交offset
        for (ConsumerRecord<String, String> record : records) {
            String topic = record.topic();
            String key = record.key();
            String value = record.value();
            logger.info("listenTestSubmitOffset msg from {},key is:{},value is:{}",topic,key,value);
        }
    }


    class processRecord implements Runnable{

        @Override
        public void run() {
            for (int i=0; i < 10; i++){
                logger.info("processing..................");
            }
        }
    }
}
