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

    int deleteUserById(int id);

    int deleteByUids(List<Integer> ids);

    int updateUserById(User u);

    int saveUser(User u);
}
