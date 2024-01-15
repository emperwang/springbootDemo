package com.stu.manage.controller;

import com.stu.manage.entiry.User;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 21:41
 * @Description
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @GetMapping(value = "list")
    public List<User> getUser(){
        List<User> users = userService.listUsers();
        return users;
    }

}
