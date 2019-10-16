package com.wk.web.controller;

import com.wk.bean.MonthSum;
import com.wk.web.service.MonthSumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("group")
public class GroupController {

    @Autowired
    private MonthSumService monthSumService;

    /**
     *  返回group首页
     * @return
     */
    @GetMapping("index.do")
    public String groupIndex(){

        return "group/index";
    }

    /**
     *  返回添加的页面
     * @return
     */
    @GetMapping("toAdd.do")
    public String groupToAdd(){

        return "group/add";
    }

    /**
     *  真正的添加操作
     * @param id
     * @param groupName
     * @param month
     * @param endPersonCount
     * @return
     */
    @PostMapping("groupAdd.do")
    @ResponseBody
    public String groupAddAction(Integer id,String groupName,Integer month,Integer endPersonCount,Integer depentsId){
        log.info("receive param id="+ id+ ", groupName="+groupName+", month="+month+", endPersonCount="+endPersonCount +
        ", depentsId=" +depentsId);
        if (id == null) {
            MonthSum monthSum = new MonthSum(groupName, month, endPersonCount,depentsId);
            monthSumService.addGroup(monthSum);
        }else{
            MonthSum monthSum = new MonthSum(id,groupName, month, endPersonCount,depentsId);
            monthSumService.updateGroupInfo(monthSum);
        }
        return "success";
    }

    /**
     *  删除操作
     * @param ids
     * @return
     */
    @PostMapping(value = "groupDelete.do")
    @ResponseBody
    public String groupdelete(@RequestParam(required = true) Integer[] ids){
        log.info("groupdelete param is:"+ Arrays.asList(ids));
        monthSumService.batchDeleteGroup(Arrays.asList(ids));
        return "success";
    }

    /**
     *  返回 数据更新的页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("toUpdate.do")
    public String toGroupUpdate(Model model,Integer id){
        log.info("toGroupUpdate receive param id = "+id);
        MonthSum monthSum = monthSumService.selectById(id);
        model.addAttribute("item",monthSum);
        return "group/update";
    }

    /**
     *  返回 上传 excel的页面
     * @return
     */
    @GetMapping(value = "toUploadExcel.do")
    public String toUploadExcel(){

        return "group/uploadExcel";
    }
}
