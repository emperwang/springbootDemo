package com.wk.web.controller;

import com.wk.bean.views.DepentmentVo;
import com.wk.web.service.DepentmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dept")
public class DepentmentController {

    @Autowired
    private DepentmentService depentmentService;

    @GetMapping(value = "index.do")
    public String toIndex(){

        return "depment/index";
    }

    @GetMapping(value = "toAdd.do")
    public String toAdd(){

        return "depment/add";
    }


    @GetMapping(value = "toUpdate.do")
    public String toUpdate(Model model , Integer id){
        DepentmentVo depVo = depentmentService.selectByid(id);
        model.addAttribute("item",depVo);
        return "depment/update";
    }
}
