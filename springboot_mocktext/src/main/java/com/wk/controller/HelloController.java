package com.wk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("getstr.do")
    @ResponseBody
    public String getString(){

        return "hello";
    }

    @GetMapping("getstr1.do")
    @ResponseBody
    public String getString1(){

        return "{\"name\":\"123\"}";
    }
}
