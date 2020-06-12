package com.wk.controller;

import com.wk.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private HelloService helloService;


    @GetMapping("gethello.do")
    @ResponseBody
    public String getHello(){
        System.out.println("this is user controller");
        return helloService.getHelloResult();
    }

}
