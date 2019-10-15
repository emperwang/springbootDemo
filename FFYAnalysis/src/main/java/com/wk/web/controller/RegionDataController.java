package com.wk.web.controller;

import com.wk.bean.Region;
import com.wk.bean.views.DataGradeView;
import com.wk.web.mapper.RegionMapper;
import com.wk.web.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "regiondata")
public class RegionDataController {
    @Autowired
    private RegionService regionService;

    @GetMapping(value = "getDataGride.do")
    public DataGradeView<Region> getDataGride(){
        return regionService.getAllForDataGride();
    }


}
