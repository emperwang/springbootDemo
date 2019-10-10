package com.wk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonthSumController {

    @GetMapping(value = "index.do")
    public String index(){

        return "index";
    }
}
