package com.wk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes(value = {"name","password"})
public class SessionAnnotation {


    @GetMapping(value = "insertSession")
    public String insertValue(Model model){
        model.addAttribute("name","this is sessionValue");
        model.addAttribute("password","this is sessionPassword");
        return "index";
    }

    @GetMapping(value = "clearSession")
    public String clearSessionValue(SessionStatus status){
        status.setComplete();       // 清空所有session总会难过所有的key-value
        return "index";
    }
}
