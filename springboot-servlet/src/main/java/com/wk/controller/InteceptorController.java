package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InteceptorController {

    @GetMapping("test.do")
    public String getValue(){
        return "success";
    }
}
