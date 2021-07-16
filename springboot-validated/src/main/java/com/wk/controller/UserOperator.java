package com.wk.controller;

import com.wk.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     检查异常,就会抛出异常
     */
    @PostMapping(value = "add")
    public String addUser(@Validated @RequestBody User user){

        return "success";
    }

    /*
        BindingResult  添加bindingResult,如果有参数异常,会保存到这里,具体怎么处理,就看业务需要了
     */
    @PostMapping(value = "plus")
    public String addUserInfo(@Validated @RequestBody User user, BindingResult result){
        System.out.println(result.hasErrors());
        System.out.println(result.getAllErrors());
        System.out.println(result.getModel());
        result.getFieldErrors().stream().forEach(ferror -> {
            String field = ferror.getField();
            String defaultMessage = ferror.getDefaultMessage();
            System.out.println(field + " --> " + defaultMessage);
        });
        result.getAllErrors().stream().forEach(obj -> {
            String objectName = obj.getObjectName();
            String defaultMessage = obj.getDefaultMessage();
            System.out.println(objectName+ " --> " + defaultMessage);
        });
        return "plus success";
    }
}
