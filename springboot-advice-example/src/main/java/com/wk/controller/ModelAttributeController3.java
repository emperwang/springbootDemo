package com.wk.controller;

import com.wk.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class ModelAttributeController3 {
    /**
     * 这种情况下, model 属性的名称没有指定, 它由返回类型隐式表示
     * 如:在这里返回的类型为 Book, 则model属性名称为 book,model属性值为
     * 方法的返回值
     */
    @ModelAttribute
    public Book addAccount(@RequestParam String abc){
        Book book = new Book();
        book.setName(abc);
        return book;
    }

    @GetMapping("hworld3")
    public String hWorld(){
        return "hello world2";
    }
}
