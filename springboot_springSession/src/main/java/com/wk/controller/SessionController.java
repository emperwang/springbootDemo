package com.wk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @Value("${server.port}")
    int port;

    @GetMapping(value = "set")
    public String setValue(HttpSession session){
        session.setAttribute("user","wangwu");
        return String.valueOf(port);
    }

    @GetMapping(value = "get")
    public String getValue(HttpSession session){
        return "用户:"+session.getAttribute("user")
                +",端口:"+port;
    }
}
