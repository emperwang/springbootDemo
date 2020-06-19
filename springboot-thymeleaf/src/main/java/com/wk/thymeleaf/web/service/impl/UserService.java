package com.wk.thymeleaf.web.service.impl;


import com.wk.thymeleaf.web.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Override
    public void findList() {
        System.out.println("find all user");
    }
}
