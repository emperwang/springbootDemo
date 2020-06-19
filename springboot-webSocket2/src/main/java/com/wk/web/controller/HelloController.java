package com.wk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "point.do")
    public String point(String name, Model model){
        model.addAttribute("name",name);
        return "socketPoint";
    }
}
