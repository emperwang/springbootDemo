package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("group")
public class GroupController {

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
    public String groupAddAction(String groupName,String month,String personCount){
        log.info("receive param groupName="+groupName+", month="+month+", personCount="+personCount);
        return "success";
    }

    @PostMapping(value = "groupDelete.do")
    @ResponseBody
    public String groupdelete(@RequestParam(required = true) Integer[] ids){
        log.info("groupdelete param is:"+ Arrays.asList(ids));
        return "success";
    }

    @GetMapping("update.do")
    public String groupUpdate(){

        return "group/add";
    }
}
