package com.security.demo.config1;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService{
    //此返回的user，用于和前台输入的账号和密码进行对比
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在此处可以利用用户名去数据库中进行查询，查询到了就封装成一个user对象，返回，不然就返回空。
        //此时前台登陆必须使用admin 123456账户密码才可以
        System.out.println(username);
        if (username.equals("admin")) {
            User user = new User("admin", "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
            return user;
        }
        return null;
    }
}
