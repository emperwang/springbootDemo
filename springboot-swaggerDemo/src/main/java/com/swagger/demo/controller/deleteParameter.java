package com.swagger.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: wk
 * @Date: 2021/2/2 15:52
 * @Description
 */
@Slf4j
@RestController
public class deleteParameter {

    /*
    {
    "uid":[1,2,3,4,5]
    }
     */
    @DeleteMapping("batdel")
    public String batchDel(@RequestBody String json){
        JSONObject object = JSONObject.parseObject(json);
        JSONArray uid = object.getJSONArray("uid");
        for (Object o : uid.toArray()) {
            log.info(o.toString());
        }
        return "success";
    }
}
