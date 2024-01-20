package com.stu.manage.service;

import com.stu.manage.entiry.User;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 22:27
 * @Description
 */
public interface IUserService {

    List<User> listUsers();

    int deleteUserById(long id);

    int deleteByUids(List<Long> ids);

    int updateUserById(User u);

    User saveUser(User u);
}
