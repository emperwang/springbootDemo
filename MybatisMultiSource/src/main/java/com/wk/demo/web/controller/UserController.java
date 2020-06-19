package com.wk.demo.web.controller;

import com.wk.demo.common.util.JSONUtil;
import com.wk.demo.entity.AmCollectorSource;
import com.wk.demo.entity.UserBean;
import com.wk.demo.web.service.SourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private SourceService sourceService;

    @GetMapping(value = "user.do")
    public List<UserBean> getAllUser(){
        return sourceService.getAllUser();
    }


    @GetMapping("source.do")
    public List<AmCollectorSource> getSource(){
        List<AmCollectorSource> all = sourceService.getAll();
        log.info(all.toString());
        log.info(JSONUtil.beanToJson(all));
        return all;
    }
}
