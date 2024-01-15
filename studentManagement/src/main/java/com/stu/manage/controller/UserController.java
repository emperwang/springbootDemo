package com.stu.manage.controller;

import com.stu.manage.entiry.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author: Sparks
 * @Date: 2024/1/15 21:41
 * @Description
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @ResponseBody
    @GetMapping(value = "list")
    public User getUser(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setUpdateTime(new Date());
        user.setEmail("123@qq.com");
        return user;
    }

}
