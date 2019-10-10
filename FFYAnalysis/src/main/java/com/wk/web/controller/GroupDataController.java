package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.web.service.MonthSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("groupdata")
public class GroupDataController {
    @Autowired
    private MonthSumService monthSumService;

    @GetMapping(value = "getAllinfo.do")
    public List<MonthSum> getAllGroupInfo(){
        List<MonthSum> all = monthSumService.findAll();

        return all;
    }
}
