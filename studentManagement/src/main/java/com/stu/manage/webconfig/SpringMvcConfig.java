package com.stu.manage.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Sparks
 * @Date: 2024/1/21 11:54
 * @Description
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:E:\\code-workSpace\\project-springboot\\springbootDemo\\studentManagement\\src\\main\\resources\\images\\");
    }
}
