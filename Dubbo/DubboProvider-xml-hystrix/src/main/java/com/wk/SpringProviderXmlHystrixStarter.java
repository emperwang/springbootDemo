package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ImportResource;

//@EnableDubbo  //开启dubbo的注解配置
@ImportResource(locations = {"classpath:dubbo-provider.xml"})
@SpringBootApplication
@EnableCircuitBreaker
public class SpringProviderXmlHystrixStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringProviderXmlHystrixStarter.class,args);
    }
}
