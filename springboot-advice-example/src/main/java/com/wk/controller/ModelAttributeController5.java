package com.wk.controller;

import com.wk.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelAttributeController5 {

    /**
     * 这里相当于 创建了一个属性值
     */
    @ModelAttribute("book")
    public Book addAccount(){
        Book book = new Book();
        book.setName("红楼");
        return book;
    }

    /**
     * 这里在参数中使用  ModelAttribute, 则相当于是从model中获取 属性值
     * 如果方法体上没有标注 @SessionAttributes 那么scop额就是request
     */
    @GetMapping("hworld5")
    public String hWorld(@ModelAttribute("book") Book book){
        System.out.println("book = " + book.toString());
        return "hello world2";
    }
}
