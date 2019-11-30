package com.wk.ServiceImpl;

import com.wk.Entity.User;
import com.wk.IService.IUserService;
import com.wk.mapper.mysql.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = userMapper.listAll();
        return users;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
