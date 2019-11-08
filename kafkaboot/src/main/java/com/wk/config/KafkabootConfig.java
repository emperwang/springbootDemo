package com.wk.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkabootConfig {
    /**
     *  consumerConfig kafkaProducerConfig 两个类主要是用于读取application.yml中相关配置,复用
     */
    @Autowired
    private KafkaConsumerConfig consumerConfig;

    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    /**
     *  consumer的配置
     * @return
     */
    public Map<String ,Object> consumerConfigs(){
        Map<String ,Object> config = new HashMap<>();
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.getBootStrapServer());
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,consumerConfig.isAutoCommit());
        config.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,consumerConfig.getAutoCommitInterval());
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,consumerConfig.getAutoOffsetReset());
        config.put(ConsumerConfig.GROUP_ID_CONFIG,consumerConfig.getGroupId());
        config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerConfig.getMaxPollRecords());
        return config;
    }

    /**
     * producer 的配置
     * @return
     */
    public Map<String,Object> producerConfig(){
        Map<String,Object> maps = new HashMap<>();
        maps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProducerConfig.getBootStrapServer());
        maps.put(ProducerConfig.ACKS_CONFIG,kafkaProducerConfig.getAcks());
        maps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        maps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        maps.put(ProducerConfig.RETRIES_CONFIG,kafkaProducerConfig.getRetries());

        return maps;
    }

    /**
     *  用于产出kakfaConsumer ,工厂类
     * @return
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>>
    kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(10);
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(1500);
        factory.setBatchListener(true);
//        factory.getContainerProperties().setAckMode();
        return factory;
    }

    public ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     *  produer的工厂类
     * @return
     */
    public ProducerFactory<String,String> producerFactory(){
        DefaultKafkaProducerFactory<String,String> kafkaProducerFactory =
                new DefaultKafkaProducerFactory(producerConfig());
        return kafkaProducerFactory;
    }

    /**
     *  kafkaTemplate
     * @return
     */
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){

        return new KafkaTemplate<String, String>(producerFactory());
    }
}
