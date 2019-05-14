package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:dubbo-consumer.xml"})
@SpringBootApplication
public class SpringConsumerXmlStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringConsumerXmlStarter.class,args);
    }
}
