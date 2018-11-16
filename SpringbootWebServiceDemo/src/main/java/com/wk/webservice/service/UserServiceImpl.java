package com.wk.webservice.service;

import com.wk.webservice.entity.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@WebService(serviceName = "UserService",   //发布的接口的名称
        targetNamespace = "http://service.webservice.wk.com/",  //接口的地址  一般是接口的包名倒序
        endpointInterface = "com.wk.webservice.service.UserService")// 接口的全限定类名
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
