package com.wk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wk
 * @Date: 2020/10/14 18:04
 * @Description
 */
@Slf4j
@RestController
public class PropertiesController {
    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${wk.value}")
    private String value;

    @GetMapping("getact")
    public String getActive(){
        log.info("active profile: {}", profile);
        return profile;
    }


    @GetMapping("getval")
    public String getValue(){
        log.info("value: {}", value);
        return value;
    }
}
