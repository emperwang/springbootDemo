package com.wk.globalInitBinder;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class gBinder {

    @InitBinder("b")
    public void b(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
    }

    @InitBinder("a")
    public void a(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }
}
