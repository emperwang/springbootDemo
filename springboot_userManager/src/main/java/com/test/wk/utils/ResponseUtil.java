package com.test.wk.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    public static ResponseEntity getResponse(HttpStatus status, String message, String errCode){
        Map<String,String> map = new HashMap<>();
        map.put("code",errCode);
        map.put("message",message);
        log.info("response msg:{}, code:{}",message, status.toString());
        return new ResponseEntity(map,status);
    }

    public static ResponseEntity getResponse(HttpStatus status,String message){
        Map<String,String> map = new HashMap<>();
        map.put("code",status.toString());
        map.put("message",message);
        log.info("response msg:{}, code:{}",message, status.toString());
        return new ResponseEntity(map,status);
    }

    public static ResponseEntity getResponse(HttpStatus status){
        log.info("code ={}", status.toString());
        return new ResponseEntity(status);
    }
}
