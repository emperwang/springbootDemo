package com.wk.controller;

import com.wk.service.AsyncServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AsyncController {
    @Resource
    private AsyncServiceImpl asyncService;

    @GetMapping("doa")
    public String doTaskA() throws InterruptedException {
        System.out.println("doTaskA controller.");
        asyncService.doTaskA();

        return "do TaskA success";
    }

    @GetMapping("dob")
    public String doTaskB() throws InterruptedException {
        System.out.println("doTaskA controller.");
        asyncService.doTaskB();

        return "do TaskB success";
    }
}
