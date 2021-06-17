package com.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Sparks
 * @Date: 2021/6/17 21:38
 * @Description
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.wk.mapper"})
public class MpStarter {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        application.run(MpStarter.class);
    }
}
