package com.security.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController {

    @GetMapping("index.do")
    public String index(){

        return "index";
    }
    @RequestMapping(value = "/login_form.do",method = RequestMethod.POST)
    @ResponseBody
    public UserDetails login_process(String username, String password){
        System.out.println("username:"+username);
        System.out.println("password"+password);

        return null;
    }

    @RequestMapping("/login")
    public String login(){

        return "login";
    }
/*    @PostMapping("/login")
    public String logins(){

        return "login";
    }*/

    @GetMapping("/login-error.do")
    public String login_error(){
        return "login_error";
    }

    @GetMapping("/success.do")
    public String success(){

        return "success";
    }
}
