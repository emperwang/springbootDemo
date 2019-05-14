package com.wk;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@EnableDubbo  //开启dubbo的注解配置
@ImportResource(locations = {"classpath:dubbo-provider.xml"})
@SpringBootApplication
public class SpringProviderXmlStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringProviderXmlStarter.class,args);
    }
}
