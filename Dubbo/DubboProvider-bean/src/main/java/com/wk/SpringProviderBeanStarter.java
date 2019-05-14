package com.wk;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDubbo  //开启dubbo的注解配置
@SpringBootApplication
@EnableDubbo(scanBasePackages = {"com.wk.service"})  //扫描暴露的服务
public class SpringProviderBeanStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringProviderBeanStarter.class,args);
    }
}
