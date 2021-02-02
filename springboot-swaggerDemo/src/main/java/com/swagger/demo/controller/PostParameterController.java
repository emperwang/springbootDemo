package com.swagger.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.swagger.demo.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wk
 * @Date: 2021/2/2 14:45
 * @Description
 */
@Api(value = "post parameter controller")
@Slf4j
@RestController
public class PostParameterController {

    @ApiOperation(value = "post parameter ")
    @PostMapping(value = "post",consumes = {"application/json"}, produces = {"application/json"})
    public String postParam(@RequestBody UserInfo userInfo){
        log.info("receive post parameter: {}", userInfo.toString());
        return userInfo.toString();
    }

    @ApiOperation(value = "post parameter JSONObject ")
    @PostMapping(value = "objpost",consumes = {"application/json"}, produces = {"application/json"})
    public String postParamobj(@RequestBody JSONObject obj){
        log.info("postParamobj parameter: {}",obj.toJSONString());
        return obj.toJSONString();
    }

    @ApiOperation(value = "put parameter JSONObject ")
    @PutMapping(value = "objput",consumes = {"application/json"}, produces = {"application/json"})
    public String putObj(@RequestBody JSONObject obj){
        log.info("postParamobj parameter: {}",obj.toJSONString());
        return obj.toJSONString();
    }
}
