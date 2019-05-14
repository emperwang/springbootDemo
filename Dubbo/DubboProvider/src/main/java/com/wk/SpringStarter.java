package com.wk;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo  //开启dubbo的注解配置
@SpringBootApplication
public class SpringStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringStarter.class,args);
    }
}
