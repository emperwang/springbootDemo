package com.wk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Sparks
 * @Date: 2021/5/9 15:08
 * @Description
 */
@SpringBootApplication
public class ScheduleMain {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(ScheduleMain.class);
    }
}
