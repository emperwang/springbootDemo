package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: wk
 * @Date: 2020/10/27 16:01
 * @Description
 */
@SpringBootApplication
public class DemoStarter {
    public static void main(String[] args) {
        System.setProperty("project.name","demo-sentinel");
        SpringApplication.run(DemoStarter.class, args);
    }
}
