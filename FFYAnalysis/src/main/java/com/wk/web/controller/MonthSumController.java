package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.web.service.MonthSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MonthSumController {

    @Autowired
    private MonthSumService monthSumService;

    @ResponseBody
    @GetMapping(value = "getAllinfo.do")
    public List<MonthSum> getAllGroupInfo(){
        List<MonthSum> all = monthSumService.findAll();

        return all;
    }
}
