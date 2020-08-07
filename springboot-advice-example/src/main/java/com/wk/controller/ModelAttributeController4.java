package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelAttributeController4 {

    /**
     * 此处方法的返回值并不是表示一个视图的名称，而是model属性的值，视图名字由
     * RequestToViewNameTranslator 根据请求hworld4 转换hworld4
     *
     * model属性名称为 attrName, key=attrName，value=hi
     */
    @GetMapping("hworld4")
    @ModelAttribute("attrName")
    public String helloworld(){

        return "hi";
    }
}
