package com.wk;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 首先通过扫描,来配置由注解生成的servlet: @WebServlet  WebFilter  WebListener
//@ServletComponentScan
public class ServletStarter {
    public static void main(String[] args) {
        //SpringApplication.run(ServletStarter.class, args);
        SpringApplication application = new SpringApplication(ServletStarter.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
