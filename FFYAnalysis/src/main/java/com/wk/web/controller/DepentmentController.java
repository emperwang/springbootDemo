package com.wk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dept")
public class DepentmentController {


    @GetMapping(value = "index.do")
    public String toIndex(){

        return "depment/index";
    }
}
