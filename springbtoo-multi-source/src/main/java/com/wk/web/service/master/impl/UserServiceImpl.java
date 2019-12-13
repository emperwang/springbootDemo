package com.wk.web.service.master.impl;

import com.wk.entity.master.UserBean;
import com.wk.mapper.master.UserBeanMapper1;
import com.wk.web.service.master.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBeanMapper1 userBeanMapper;

    @Override
    public List<UserBean> getAll() {
        return userBeanMapper.selectByExample(null);
    }
}
