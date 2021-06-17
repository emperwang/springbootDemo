package com.wk.controller;

import com.wk.entity.Users;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/6/17 21:40
 * @Description
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "all.do")
    public List<Users> getAllUsers(){
        System.out.println("userController getAllUsers run.");
        List<Users> all = userService.getAll();
        return all;
    }
}
