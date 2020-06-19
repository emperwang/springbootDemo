package com.wk.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        // 允许所有访问
        //http.authorizeRequests().anyRequest().permitAll();

        // 访问role1路径的必须由 role1角色, role2路径必须由role2角色
        http.authorizeRequests()
                .antMatchers("/").permitAll()       // 首页允许所有人访问
                .antMatchers("/role1/**").hasRole("role1")
                .antMatchers("/role2/**").hasRole("role2")
                .antMatchers("/role3/**").hasRole("role3");

        // 没有权限转到登陆页
        http.formLogin();

        // 开启注销, 注销成功后 跳转到  index
        http.logout().logoutSuccessUrl("/index");
        // 定制登陆页面，设置了登陆页面的登陆地址，成功后的登陆地址，失败后的地址，
        // 以及登陆时 的用户和密码的参数名
        /*http.formLogin().loginPage("/").successForwardUrl("/success").failureForwardUrl("/fail")
                .passwordParameter("username").passwordParameter("pwd");*/

        // remember Me 键
        http.rememberMe();
    }

    // 设置一些内存中的账户密码，用于验证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().passwordEncoder(encoder)
                .withUser("role1")
                .password(encoder.encode("123456"))
                .roles("role1")
                .and()
                .withUser("role2")
                .password(encoder.encode("123456"))
                .roles("role2")
                .and()
                .withUser("role3")
                .password(encoder.encode("123456"))
                .roles("role3")
                .and()
                .withUser("root")
                .password(encoder.encode("123456"))
                .roles("role1","role2","role3");
    }
}
