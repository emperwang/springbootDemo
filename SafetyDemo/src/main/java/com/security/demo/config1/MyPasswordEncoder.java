package com.security.demo.config1;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
// 此编解码是为了让前台的账户密码进行指定的解密来进行对比
public class MyPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword) {
        String password = rawPassword.toString();
        return password;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        boolean flag = encodedPassword.equals(rawPassword.toString());
        return flag;
    }
}
