package com.wk.config;

import com.wk.interceptor.SpringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private SpringInterceptor springInterceptor;

    private String[] staticResources = {"/static/**","/login"};

    // 添加拦截器到容器中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(springInterceptor).addPathPatterns("/**")
                .excludePathPatterns(staticResources);
    }
}
