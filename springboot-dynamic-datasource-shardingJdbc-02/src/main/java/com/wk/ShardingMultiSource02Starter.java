package com.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = {"com.wk.mapper"})
public class ShardingMultiSource02Starter {
    public static void main(String[] args) {
        SpringApplication.run(ShardingMultiSource02Starter.class,args);
    }
}
