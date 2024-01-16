package com.stu.manage.controller;

import com.stu.manage.entiry.User;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 21:41
 * @Description
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "list")
    public List<User> getUser(){
        List<User> users = userService.listUsers();
        return users;
    }


    @DeleteMapping(value = "deleteId/{id}")
    public int deleteUserById(@PathVariable(name = "id") int id){
        return userService.deleteUserById(id);
    }


    @DeleteMapping(value = "deleteIds")
    public int deleteUserByMultipleIds(@RequestParam(name = "ids",required = true) List<Integer> ids){
        return userService.deleteByUids(ids);
    }


    @PutMapping(value = "updateById")
    public int updateUser(@RequestBody User u){
        return userService.updateUserById(u);
    }


    @PostMapping(value = "save")
    public int saveUser(@RequestBody User u){
        return userService.saveUser(u);
    }

}
