package com.wk.web.controller;

import com.wk.bean.Region;
import com.wk.web.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping(value = "region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "index.do",method = RequestMethod.GET)
    public String toIndex(){

        return "/region/index";
    }

    @RequestMapping(value = "toAdd.do",method = RequestMethod.GET)
    public String toAdd(){

        return "/region/add";
    }

    @RequestMapping(value = "toUpdate.do",method = RequestMethod.GET)
    public String toUpdate(Model model, Integer id){
        log.info("toUpdate.do receive parameter, id = {}",id);
        Region region = regionService.selectById(id);
        model.addAttribute("item",region);
        return "/region/update";
    }
}
