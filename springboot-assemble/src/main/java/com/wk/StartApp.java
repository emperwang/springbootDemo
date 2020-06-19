package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
        // 设置支持ipv6地址
        System.setProperty("java.net.preferIPv6Addresses","true");
        SpringApplication.run(StartApp.class,args);
    }
}
