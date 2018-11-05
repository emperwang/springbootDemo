package com.wk.demo.config;

import com.wk.demo.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Autowired
    private MyInterceptor interceptor;

    private String[] staticResources = {"/static/**","/login"};

    /**
     * addPathPatterns 表示对什么路径进行拦截  这里是对所有路径拦截
     * excludePathPatterns 表示静态资源不拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(interceptor).addPathPatterns("/**")
            .excludePathPatterns(staticResources);
    }

    /**
     * 设置不需要经过controller就跳转到登陆页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }


}
