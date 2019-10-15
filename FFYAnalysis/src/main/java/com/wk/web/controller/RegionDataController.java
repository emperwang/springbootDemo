package com.wk.web.controller;

import com.wk.bean.Region;
import com.wk.bean.views.ComboVo;
import com.wk.bean.views.DataGradeView;
import com.wk.constant.ResponseMsg;
import com.wk.web.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "regiondata")
public class RegionDataController {
    @Autowired
    private RegionService regionService;

    @GetMapping(value = "getDataGride.do")
    public DataGradeView<Region> getDataGride(){
        return regionService.getAllForDataGride();
    }

    @PostMapping(value = "regionDelete.do")
    public String regionDelete(@RequestParam(required = true) Integer[] ids){
        log.info("regionDelete receive msg is {}", Arrays.asList(ids).toString());
        Map<String, String> msg = regionService.batchDeleteReturnMsg(Arrays.asList(ids));
        if (msg.containsKey("message")){
            return msg.get("message");
        }
        return ResponseMsg.Success;
    }

    @PostMapping(value = "regionAdd.do")
    public String regionAdd(String name){
        log.info("regionAdd.do receive parame is:name = {}",name);
        int count = regionService.insertRecord(name);
        if (count > 0) {
            return ResponseMsg.Success;
        } else {
            return ResponseMsg.Failed;
        }
    }

    @PostMapping(value = "regionUpdate.do")
    public String regionUpdate(Integer id,String name){
        log.info("regionUpdate.do receive parame is:name = {},id = {}",name,id);
        int count = regionService.updateRecord(id, name);
        if (count > 0) {
            return ResponseMsg.Success;
        } else {
            return ResponseMsg.Failed;
        }
    }

    @GetMapping(value = "regionCombo.do")
    public List<ComboVo> getRegionCombo(){
        return regionService.getRegionCombo();
    }
}
