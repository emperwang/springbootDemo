package com.wk.demo.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

// 约定的控制器的写法
public class ControllerInterface implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("hello this is controller interface");
        return null;
    }
}
