package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CallbackController {

    @GetMapping("/callback")
    public String login(@RequestParam("code") String code){
        return "假装这里有一个页面";
    }
}
