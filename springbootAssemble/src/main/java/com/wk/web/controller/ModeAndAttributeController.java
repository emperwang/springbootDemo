package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ModeAndAttributeController {


    //@ModelAttribute(value = "myName")  // 也可以自己指定名字
    @ModelAttribute
    public void getParame(@RequestParam String name){
        log.info("receive name is{}",name);
    }

    @GetMapping(value = "modelAttr")
    public String testModeAttr(){

        return "index";
    }
}
