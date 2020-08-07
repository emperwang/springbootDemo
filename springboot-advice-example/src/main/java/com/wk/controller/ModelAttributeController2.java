package com.wk.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ModelAttributeController2 {
    /**
     * 此处指定了model的属性名字,这里属性名为 attrName,值为方法返回值
     */
    @ModelAttribute("attrName")
    public String addAccount(@RequestParam String abc){
        return abc;
    }

    @GetMapping("hworld2")
    public String hWorld(){
        return "hello world2";
    }
}
