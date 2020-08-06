package com.wk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReqestBodyAndResponseBodyController {

    @PostMapping("rbody")
    public String restBody(@RequestBody String str){
        System.out.println("receive body : " + str);
        return str;
    }


    @GetMapping("res")
    public String responseBody(){
        System.out.println("responseBody run..");

        return "run";
    }
}