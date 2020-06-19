package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    // hystrix降级测试桩 程序
    @GetMapping(value = "getOrder.do")
    public String getOrder() throws InterruptedException {
        Thread.sleep(5000);
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
