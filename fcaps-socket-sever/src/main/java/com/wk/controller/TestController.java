package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "test.do")
    public String getStr(){

        return "this is testre";
    }
}
