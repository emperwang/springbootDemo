package com.wk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 首先通过扫描,来配置由注解生成的servlet: @WebServlet  WebFilter  WebListener
@ServletComponentScan
public class ServletStarter {
    public static void main(String[] args) {
        SpringApplication.run(ServletStarter.class, args);
    }
}
