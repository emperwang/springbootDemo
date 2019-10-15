package com.wk.web.controller;

import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.DepentmentVo;
import com.wk.web.service.DepentmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "deptdata")
public class DepentmentDataController {
    @Autowired
    private DepentmentService depentmentService;

    @GetMapping(value = "getDataGride.do")
    public DataGradeView<DepentmentVo> getDataGride() {
        return  depentmentService.getDataGride();
    }
}
