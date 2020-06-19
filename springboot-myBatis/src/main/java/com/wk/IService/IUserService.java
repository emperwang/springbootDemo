package com.wk.IService;

import com.wk.Entity.User;

import java.util.List;

public interface IUserService {
    void add(User user);
    List<User> findAll();
    User findById(Integer id);
}
