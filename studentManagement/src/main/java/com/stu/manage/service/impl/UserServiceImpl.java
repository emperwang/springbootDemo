package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entity.Role;
import com.stu.manage.entity.User;
import com.stu.manage.mapper.RoleMapper;
import com.stu.manage.mapper.UserMapper;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private Map<Long,User> userCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void postOperation(){
        List<User> users = listUsers();
        users.stream().forEach(u -> {
            userCache.put(u.getUid(), u);
        });
    }

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
    public int deleteUserById(long id) {
        int i = userMapper.deleteById(id);
        return i;
    }

    @Override
    public int deleteByUids(List<Long> ids) {
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
    public User saveUser(User u) {
        if (StringUtils.isEmpty(u.getRids())){
            u.setRids("2");
        }

        u.setCreateTime(formatter.format(LocalDateTime.now()));
        u.setUpdateTime(formatter.format(LocalDateTime.now()));
        int ct = userMapper.insert(u);
        return ct>0?u:null;
    }

    @Override
    public List<User> batchInsert(List<User> users) {
        // filter内存中没有的数据
        List<User> list = users.stream().filter(u -> !userCache.containsKey(u.getUid())).collect(Collectors.toList());
        // 设置默认配置
        list.stream().forEach(u -> {
            if (StringUtils.isEmpty(u.getPassword())){
                u.setPassword("123456");
            }
            if (StringUtils.isEmpty(u.getEmail())){
                u.setEmail("123@163.com");
            }
            if (StringUtils.isEmpty(u.getRids())){
                u.setRids("2");
            }
            if (StringUtils.isEmpty(u.getPhoneNumber())){
                u.setPhoneNumber("17111912011");
            }
        });
        userMapper.batchInsert(list);
        return list;
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
