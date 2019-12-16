package com.wk.web.service.master.impl;

import com.wk.entity.master.UserBean;
import com.wk.mapper.master.UserBeanMapper1;
import com.wk.web.service.master.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBeanMapper1 userBeanMapper;

    @Override
    public List<UserBean> getAll() {
        return userBeanMapper.selectByExample(null);
    }

    @Override
    public List<UserBean> getPages(Integer offset, Integer limits) {
        return userBeanMapper.selectPages(offset,limits);
    }

    @Override
    public List<UserBean> getPageWithMap(Map<String, Object> maps) {
        return userBeanMapper.selectPagesWithMap(maps);
    }
}
