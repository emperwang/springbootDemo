package com.wk.thymeleaf.web.controller;

import com.wk.thymeleaf.bean.Student;
import com.wk.thymeleaf.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class ThymeLeafController {

    @Autowired
    private IUserService userService;

    @GetMapping("finduser.do")
    @ResponseBody
    public String findUser(){
        userService.findList();
        return "success";
    }

    @GetMapping("test.do")
    @ResponseBody
    public String test(){

        return "test ok";
    }

    @GetMapping("index.do")
    public String index(){
        System.out.println("index function......");
        return "index";
    }

    @GetMapping("thymeleaf1.do")
    public String thymeleaf1(Map map){
        Student stu = new Student();
        stu.setId("1");
        stu.setName("wk");
        stu.setScore("1000");

        map.put("student",stu);
        map.put("thText","thText-value");
        map.put("thUText","thUText-value");
        map.put("thValue","thValueValue");
        map.put("thEach", Arrays.asList("first","second","third","fourth"));
        map.put("thIf","ifvalue");
        return "thymeleaf1";
    }

    @GetMapping("thymeleaf2.do")
    public String thymeleaf2(){
        System.out.println("into thymeleaf2");
        return "thymeleaf2";
    }
}
