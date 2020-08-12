package com.wk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExecutorStarter {
    public static void main(String[] args) {
        SpringApplication context = new SpringApplication(ExecutorStarter.class);
        context.setBannerMode(Banner.Mode.OFF);
        context.run(args);
    }
}
