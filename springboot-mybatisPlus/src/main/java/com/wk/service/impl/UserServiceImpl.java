package com.wk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wk.entity.Users;
import com.wk.mapper.UserMapper;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2021/6/17 22:07
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Users> getAll() {
        System.out.println("UserServiceImpl getAll run");
        List<Users> users = userMapper.selectList(null);
        return users;
    }
}
