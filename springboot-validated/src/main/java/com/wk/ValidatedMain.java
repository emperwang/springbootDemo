package com.wk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:33
 * @Description
 */
@SpringBootApplication
public class ValidatedMain {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(ValidatedMain.class);
    }
}
