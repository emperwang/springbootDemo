package com.wk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdviceStarter {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AdviceStarter.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
