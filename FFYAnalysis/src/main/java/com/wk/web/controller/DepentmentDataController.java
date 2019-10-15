package com.wk.web.controller;

import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.DepentmentVo;
import com.wk.constant.ResponseMsg;
import com.wk.web.service.DepentmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "deptdata")
public class DepentmentDataController {
    @Autowired
    private DepentmentService depentmentService;

    @GetMapping(value = "getDataGride.do")
    public DataGradeView<DepentmentVo> getDataGride() {
        return  depentmentService.getDataGride();
    }

    @PostMapping(value = "deptDelete.do")
    public String deptDelete(@RequestParam(required = true) Integer[] ids){
        log.info("deptDelete receive msg is {}", Arrays.asList(ids).toString());
        Map<String, String> msg = depentmentService.batchDeleteReturnMsg(Arrays.asList(ids));
        if (msg != null && msg.containsKey("message")){
            String message = msg.get("message");
            return message;
        }
        return ResponseMsg.Success;
    }

    @PostMapping(value = "deptAdd.do")
    public String deptAdd(String deptName,Integer regionId){
        log.info("deptAdd receive parameter,deprName = {},regionId = {}", deptName,regionId);

        int count = depentmentService.insertRecord(deptName,regionId);
        if (count > 0){
            return ResponseMsg.Success;
        }else{
            return ResponseMsg.Failed;
        }
    }

    @PostMapping(value = "deptUpdate.do")
    public String deptUpdate(Integer id,String deptName,Integer regionId) {
        log.info("deptUpdate receive parameter,deptName = {},regionId = {},id = {}", deptName, regionId, id);
        int count = depentmentService.updateRecord(id, deptName, regionId);
        if (count > 0) {
            return ResponseMsg.Success;
        } else {
            return ResponseMsg.Failed;
        }
    }
}
