package com.wk.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveChineseMsg {

   // consumes = {"application/json;charset=utf-8"},
    @PostMapping(value = "chinese",
            produces = {"application/json;charset=utf-8"})
    public String getChineseMsg(@RequestBody String  param){

        System.out.println("recive msg: "+param);

        return "success";
    }
}
