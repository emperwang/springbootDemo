package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class ModelAttrController {

    /**
     *  @ModelAttribute(name = "name")  表示会把参数绑定到model，可用于前台
     *  BindingResult：表示绑定的结果
     * @param name
     * @param result
     * @return
     */
    @GetMapping("attr.do")
    public String modelAttr(@ModelAttribute(name = "name") String name, BindingResult result){
        log.info("result :{}",result.hasErrors());
        return "index";
    }
}
