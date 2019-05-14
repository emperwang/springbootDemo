package com.wk.controller;

import com.wk.api.bean.User;
import com.wk.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(@RequestParam("uid") Integer id){
        User user = userService.getUser(id);
        return user;
    }
}
