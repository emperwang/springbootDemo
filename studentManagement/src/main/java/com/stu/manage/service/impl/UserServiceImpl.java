package com.stu.manage.service.impl;

import com.stu.manage.entiry.User;
import com.stu.manage.mapper.UserMapper;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 22:27
 * @Description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }
}
