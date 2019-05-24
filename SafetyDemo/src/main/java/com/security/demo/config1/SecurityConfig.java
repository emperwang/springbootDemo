package com.security.demo.config1;

import com.security.demo.provider.MytAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//版本一
@Configuration
@EnableWebSecurity   //打开web安全
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    MyPasswordEncoder passwordEncoder;
    @Autowired
    MytAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表示登陆页面和登陆处理的函数以及错误页面允许直接访问
        //loginProcessingUrl("/login/form.do")  处理登陆的url
        //loginPage("/login.do").
        //successForwardUrl("/success.do")
        //failureUrl("/login-error.do").permitAll()
        http.formLogin()
                .loginPage("/login").defaultSuccessUrl("/success.do").failureUrl("/login-error.do").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()  //所有请求需要登陆
                .and()
                .csrf().disable();    //关闭跨站访问保护

    }

    //此用于把指定的用户 使用指定的解密方式
    @Autowired
    public void configUser(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.eraseCredentials(false);*/
        //自定义认证逻辑
        auth.authenticationProvider(authenticationProvider);
    }
}
