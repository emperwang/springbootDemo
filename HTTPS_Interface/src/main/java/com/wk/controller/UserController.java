package com.wk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("getUser")
    public ResponseEntity getUser(){
        Map<String,String> entityMap = new HashMap<>();
        entityMap.put("name","zhangsan");
        entityMap.put("age","20");
        entityMap.put("address","bj");
        return new ResponseEntity(entityMap,HttpStatus.OK);
    }
}
