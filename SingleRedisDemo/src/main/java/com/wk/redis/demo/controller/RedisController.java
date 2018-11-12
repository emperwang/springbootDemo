package com.wk.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("insert.do")
    @ResponseBody
    public String testInsert(){
        redisTemplate.opsForValue().set("keyb","testkey");
        Object keya = redisTemplate.opsForValue().get("keyb");
        return keya.toString();
    }
}
