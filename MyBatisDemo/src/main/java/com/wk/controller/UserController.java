package com.wk.controller;

import com.wk.Entity.User;
import com.wk.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/listUser",method=RequestMethod.GET)
    @ResponseBody
    public List<User> listUser(){
        List<User> all = userService.findAll();
        return all;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "this is test";
    }
}