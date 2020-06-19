package com.wk.web;

import com.wk.config.ElasticParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ElasticParameter parameter;

    @GetMapping("listuser.do")
    public String listUser(){
        return "list All user";
    }

    @GetMapping("testparm.do")
    public String testparm(){
        parameter.getNamespace();
        parameter.getZkList();
        parameter.getShardingItemParamrters();
        parameter.getStockCron();
        parameter.getStockShardingTotalCount();
        return "list All user";
    }

}
