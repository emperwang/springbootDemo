package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.web.service.MonthSumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("group")
public class GroupController {

    @Autowired
    private MonthSumService monthSumService;

    @GetMapping("index.do")
    public String groupIndex(){

        return "group/index";
    }

    @GetMapping("toAdd.do")
    public String groupToAdd(){

        return "group/add";
    }
    @PostMapping("groupAdd.do")
    @ResponseBody
    public String groupAddAction(String groupName,Integer month,Integer personCount){
        log.info("receive param groupName="+groupName+", month="+month+", personCount="+personCount);
        MonthSum monthSum = new MonthSum(groupName, month, personCount);
        monthSumService.addGroup(monthSum);
        return "success";
    }

    @PostMapping(value = "groupDelete.do")
    @ResponseBody
    public String groupdelete(@RequestParam(required = true) Integer[] ids){
        log.info("groupdelete param is:"+ Arrays.asList(ids));
        monthSumService.batchDeleteGroup(Arrays.asList(ids));
        return "success";
    }

    @GetMapping("update.do")
    public String groupUpdate(){

        return "group/add";
    }
}
