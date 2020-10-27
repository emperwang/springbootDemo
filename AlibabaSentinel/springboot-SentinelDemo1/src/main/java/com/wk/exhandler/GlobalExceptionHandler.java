package com.wk.exhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wk
 * @Date: 2020/10/27 16:57
 * @Description
 */
@ControllerAdvice(basePackages = {"com.wk.controller"})
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(BlockException b){
        return "请求过于频繁";
    }
}
