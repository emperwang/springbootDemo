package com.wk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("index.do")
    public String index(){

        return "index";
    }
    @RequestMapping("socket.do")
    public String socket(){

        return "socket";
    }
}
