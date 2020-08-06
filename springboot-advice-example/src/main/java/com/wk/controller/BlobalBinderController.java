package com.wk.controller;

import com.wk.entity.Author;
import com.wk.entity.Book;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class BlobalBinderController {

    /**
     * 如果单纯靠前台传值,此容易混淆
     * 所以这里使用了全局 属性绑定器 来进行绑定
     * 此处使用的全局的 binder
     */
    @GetMapping("addbook")
    public String addBook(@ModelAttribute("b")Book b, @ModelAttribute("a")Author author){
        System.out.println(b.toString());
        System.out.println(author.toString());
        return "success";
    }

    @GetMapping("data")
    public String dataGet(@RequestParam(value="nowTime") Date nowTime){
        System.out.println("receive data: " + nowTime);
        return "nowTime";
    }


    // 局部的绑定器使用
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }



}
