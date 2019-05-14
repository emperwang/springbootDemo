package com.wk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wk.api.bean.User;
import com.wk.api.service.UserService;
import org.springframework.stereotype.Component;
//此处的service是dubbo用于暴露服务
//@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Integer id) {
        User user = new User(1, "shanghao", "2", "bj", "119", "true");
        return user;
    }
}
