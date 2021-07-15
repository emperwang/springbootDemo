package com.wk.controller;

import com.wk.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:51
 * @Description
 */
@RestController
@Slf4j
public class UserOperator {

    /*
     在这里添加的注解  用于user内部的注解校验
     */
    @PostMapping(value = "add")
    public String addUser(@Validated User user){

        return "";
    }
}
