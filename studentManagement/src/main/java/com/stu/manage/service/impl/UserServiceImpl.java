package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.Role;
import com.stu.manage.entiry.User;
import com.stu.manage.mapper.RoleMapper;
import com.stu.manage.mapper.UserMapper;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Sparks
 * @Date: 2024/1/15 22:27
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");

    @Override
    public List<User> listUsers() {
        List<User> users = userMapper.listUsers();
        users.forEach(u -> {
            List<Integer> rids = Arrays.stream(u.getRids().split(",")).map(i -> Integer.parseInt(i)).collect(Collectors.toList());
            List<Role> roles = roleMapper.selectBatchIds(rids);
            u.setRoles(roles);
        });

        return users;
    }

    @Override
    public int deleteUserById(int id) {
        int i = userMapper.deleteById(id);
        return i;
    }

    @Override
    public int deleteByUids(List<Integer> ids) {
        int count = userMapper.deleteBatchIds(ids);
        return count;
    }

    @Override
    public int updateUserById(User u) {
        u.setUpdateTime(formatter.format(LocalDateTime.now()));
        int ct = userMapper.updateById(u);
        return ct;
    }

    @Override
    public int saveUser(User u) {
        u.setCreateTime(formatter.format(LocalDateTime.now()));
        u.setUpdateTime(formatter.format(LocalDateTime.now()));
        return userMapper.insert(u);
    }


    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
