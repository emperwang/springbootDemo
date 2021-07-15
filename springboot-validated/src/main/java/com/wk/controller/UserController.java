package com.wk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:35
 * @Description
 */
@Slf4j
@RestController
// 在类上给出的 注解,会使 在接口中的校验 生效
@Validated
public class UserController {

    @GetMapping(value = "/ruser")
    public String receiveUser(){

        return "ok";
    }

    @GetMapping(value = "info")
    public String getInfo(@NotNull @NotEmpty String name, @NotNull String addr){

        return new StringBuilder(name).append(": ").append(addr).toString();
    }
}
