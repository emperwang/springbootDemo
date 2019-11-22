package com.wk.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class KafkaConsumerUtil {
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerUtil.class);

    ExecutorService executorService = new ThreadPoolExecutor(10,50,
            60, TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());

    public KafkaConsumerUtil(){
        for(int i=0;i<10;i++){
            executorService.submit(this.new processRecord());
        }
    }


    @KafkaListener(topics = "test")
    public void listenTestTopic(ConsumerRecord<String,String> record){
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        logger.info("receive msg from {},key is:{},value is:{}",topic,key,value);
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
