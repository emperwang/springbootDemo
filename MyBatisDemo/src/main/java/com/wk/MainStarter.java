package com.wk;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.wk.mapper"})
public class MainStarter {
    public static void main(String[] args) {
        SpringApplication.run(MainStarter.class,args);
    }
}
