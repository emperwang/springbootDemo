package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wk
 * @Date: 2020/10/27 16:59
 * @Description
 */
@RestController
public class DemoController {

    @GetMapping("/echo")
    public String echo(){
        return "echo";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
