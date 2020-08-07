package com.wk.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * modelAttribute 放在 void返回值的方法
 * 此example,在获得请求/hworld 后, populoateModel方法在 hWorld之前被调用
 * 它把请求参数 /hworld?abc=text 加入到一个名字 attrName 的model属性中,在它执行后 hWorld方法被调用
 * 如果返回视图的话, model已经由 @ModelAttribute 方法生产好了.
 *
 * 当然也可以  public String hWorld(){String abc) 来直接接收参数, 只是这里为了测试.
 *
 */
@RestController
public class ModelAttributeController1 {

    @ModelAttribute
    public void populoateModel(@RequestParam String abc, Model model){
        model.addAttribute("attrName", abc);
    }

    @GetMapping(value = "/hworld")
    public String hWorld(){
        return "hello world.";
    }

}
