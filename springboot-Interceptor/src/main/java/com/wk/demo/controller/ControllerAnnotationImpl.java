package com.wk.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class ControllerAnnotationImpl implements ControllerAnnotation{

    @Override
    public String test1() {
        System.out.println("********************interface annotation test*********************");
        return "success";
    }

    @GetMapping("ann2.do")
    public String test2(){
        System.out.println("********************interface annotation 2222222*********************");
        return "success2";
    }
}
