package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:dubbo-consumer.xml"})
@SpringBootApplication
@EnableCircuitBreaker
public class SpringConsumerXmlHystrixStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringConsumerXmlHystrixStarter.class,args);
    }
}
