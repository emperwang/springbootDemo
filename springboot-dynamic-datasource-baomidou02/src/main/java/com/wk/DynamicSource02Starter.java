package com.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = {"com.wk.mapper"})
public class DynamicSource02Starter {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(DynamicSource02Starter.class,args);
    }
}
