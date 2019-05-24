package com.security.demo.provider;

import com.security.demo.config1.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MytAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单提交的名字
        String userName = authentication.getName();
        //表单中提交的密码
        String password = (String) authentication.getCredentials();
        //从数据库中获取用户的账户密码以及相关的权限
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (userDetails == null){
            throw new BadCredentialsException("用户不存在");
        }
        String dbPassword = userDetails.getPassword();
        if(!password.equals(dbPassword)){
            throw new BadCredentialsException("密码不正确");
        }
        //获取用户的权限
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //表示支持这个执行
        return true;
    }
}
