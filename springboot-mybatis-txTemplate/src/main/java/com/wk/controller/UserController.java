package com.wk.controller;

import com.wk.entity.User;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "insert")
    public String insertOne(){
        User user = new User();
        user.setAddress("bj");
        user.setAge(20);
        user.setName("wangwu");
        userService.insertOne(user);
        return "success";
    }

}
