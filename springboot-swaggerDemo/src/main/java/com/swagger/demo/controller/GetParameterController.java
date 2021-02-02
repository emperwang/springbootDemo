package com.swagger.demo.controller;

import com.swagger.demo.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: wk
 * @Date: 2021/2/1 15:22
 * @Description
 */
@Api(value = "test get method parameter", tags = {"get method"})
@RestController
@Slf4j
public class GetParameterController {

    // 把参数接收到 实体类中, 此时是区分大小写的
    @ApiOperation(httpMethod = "GET", value="receive param with javabean")
    @GetMapping(value = "getuser")
    public String queryUser(UserInfo user){
        log.info("get parameter:{}", user.toString());
        return "query user";
    }

    // 把参数接收到 map中,此时可以吧map中的key全部转换为小写或大写,变相达到参数不区分大小写
    @ApiOperation(httpMethod = "GET", value="receive param with map")
    @GetMapping(value = "getusermap")
    public String queryUser2(@RequestParam Map<String,Object> user){
        log.info("get parameter map :{}", user.toString());
        return "query user";
    }
    // 通过request.getParameterMap 接收所有参数
    @ApiOperation(httpMethod = "GET", value="get param with request")
    @GetMapping(value = "getuserreq")
    public String queryUser3(HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            log.info("key :{}", entry.getKey());
            log.info("value :{}",entry.getValue());
        }
        return "query user";
    }
}
