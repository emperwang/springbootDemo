package com.wk.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetGlobalModelAttrbiteController {
    // 获取全局属性值的第一种方式, 通过注入model 来实现
    @GetMapping("getattr")
    public String getAllGlobalModelAttrbite(Model model){
        Object msg = model.asMap().get("msg");
        Object info = model.asMap().get("info");
        System.out.println("getAllGlobalModelAttrbite  first .....");
        return msg.toString() + "----" + info.toString();
    }

    // 获取全局属性的第二种方式, 通过参数注解注入来实现
    @GetMapping("getattr2")
    public String getAllGlobalAttribute(@ModelAttribute("msg")String msg,
                                        @ModelAttribute("info") Map<String,String> map){

        System.out.println("getAllGlobalAttribute  second....");
        return msg+"-----" + map.toString();
    }
}
