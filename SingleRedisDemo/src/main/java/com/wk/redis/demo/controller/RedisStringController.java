package com.wk.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/**
 * string 类型操作
 */
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
    //得到key
    @GetMapping("getkey.do")
    public String getKeyValue(){
        String value = (String) redisTemplate.boundValueOps("keyb").get();
        return value;
    }

    public String appendValue(){
        Integer keyb = redisTemplate.boundValueOps("keyb").append("123");
        return keyb.toString();
    }

    public String deleteKey(){
        Boolean keyb = redisTemplate.delete("keyb");
        return keyb.toString();
    }
}
