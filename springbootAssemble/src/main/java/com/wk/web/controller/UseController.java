package com.wk.web.controller;

import com.wk.bean.User;
import com.wk.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UseController {

    @Autowired
    private UserService userService;
    @GetMapping("getuser")
    public List<User> listUser(){
        List<User> users = userService.listUser();
        return users;
    }

    @GetMapping("configfile")
    public String testConfigFile() throws IOException {
        //GetConfigValue test1 = GetConfigValue.GetInstace();
        //String test11 = test1.getValue("test1");
        return "";
    }

    @DeleteMapping(value = "del.do",consumes = {"application/json;charset=UTF-8"})
    public String getDeleteEntityBody(@RequestBody String body){
        System.out.println("receive msg :" + body);
        return "success";
    }
}
