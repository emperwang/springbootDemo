package com.wk.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wk.api.bean.User;
import com.wk.api.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl {

    @Reference
    UserService userService;

    public User getUser(Integer id){
        User user = userService.getById(id);
        return user;
    }
}
