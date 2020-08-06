package com.wk.demo.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HandlerRequestImpl implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println("this is HttpRequestHandler");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write("this is HttpRequestHandler");
        writer.flush();
    }
}
