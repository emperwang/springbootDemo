package com.wk.config;

import com.wk.servlet.ServletDemo2;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    // 通过bean来创建一个servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        return new ServletRegistrationBean<ServletDemo2>(new ServletDemo2(), "/demo2");
    }
}
