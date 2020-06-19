package com.wk.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  druid控制界面设置
 */
@Configuration
public class DruidWebConfig {

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        // 注册服务
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        // 白名单(为空表示所有都可以访问，多个IP的时候用逗号隔开)
        registrationBean.addInitParameter("allow","127.0.0.1");

        // IP黑名单(共同存在时，deny优先于allow；黑名单中的ip无法登陆可视化界面)
        registrationBean.addInitParameter("deny","127.0.0.2");
        // 设置登录用户的用户名和密码
        registrationBean.addInitParameter("loginUsername","root");
        registrationBean.addInitParameter("loginPassword","admin");
        // 是否能够重置数据
        registrationBean.addInitParameter("resetEnable","false");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        System.out.println("druid初始化成功");
        return filterRegistrationBean;
    }

}
