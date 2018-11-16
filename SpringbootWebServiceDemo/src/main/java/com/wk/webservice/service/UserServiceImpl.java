package com.wk.webservice.service;

import com.wk.webservice.entity.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(serviceName = "UserService",
        targetNamespace = "http://service.webservice.wk.com/",
        endpointInterface = "com.wk.webservice.service.UserService")
@Component
public class UserServiceImpl implements UserService{

    @Override
    public User getUser() {
        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
        return user;
    }
}
