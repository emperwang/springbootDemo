package com.stu.manage;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Sparks
 * @Date: 2024/1/15 21:39
 * @Description
 */

@SpringBootApplication
public class AppStarter {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(AppStarter.class);
    }
}
