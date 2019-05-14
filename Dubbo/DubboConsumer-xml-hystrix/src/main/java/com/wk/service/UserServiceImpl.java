package com.wk.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wk.api.bean.User;
import com.wk.api.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl {

    @Reference
    UserService userService;
    @HystrixCommand(fallbackMethod = "hello")
    public User getUser(Integer id){
        User user = userService.getById(id);
        return user;
    }

    public User hello(Integer id){
        User user = new User(1, "exception", "2", "wk", "119", "true");
        return user;
    }
}
