package com.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.wk.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class DynamicSourceStarter {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(DynamicSourceStarter.class,args);
    }
}
