package com.wk.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class InterceptorController {

    /** 拦截器测试结果
     *  preHandler...........
        test function
        postHandler..........
        afterCompletion............
     */
    @GetMapping("test.do")
    @ResponseBody
    public String test(){
        System.out.println("test function");
        return "test";
    }

    /**
     * 拦截器测试结果：
     * login function       可见这个访问没有经过拦截器
     * @return
     */
    @GetMapping("login")
    public String login(){
        System.out.println("login function");
        return "login";
    }

    /**
     * 拦截器测试结果
     preHandler...........
    index function
    postHandler..........
    afterCompletion............
    */
    @GetMapping("index.do")
    public String index(){
        System.out.println("index function");
        return "index";
    }
}
