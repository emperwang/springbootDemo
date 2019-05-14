package com.wk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wk.api.bean.User;
import com.wk.api.service.UserService;
import org.springframework.stereotype.Component;


//此处的service是dubbo用于暴露服务
//@Service
@Component
public class UserServiceImpl implements UserService {

    @HystrixCommand
    @Override
    public User getById(Integer id) {
        System.out.println("服务被调用");
        User user = new User(1, "shanghao", "2", "bj", "119", "true");
        if (Math.random()>0.5){
            throw new RuntimeException("user imple exception..");
        }
        return user;
    }
}
