package com.wk.demo.web.service.impl;

import com.wk.demo.entity.AmCollectorSource;
import com.wk.demo.entity.UserBean;
import com.wk.demo.mapper.mysql.UserBeanMapper;
import com.wk.demo.mapper.postgresql.AmCollectorSourceMapper;
import com.wk.demo.web.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private UserBeanMapper userBeanMapper;
    @Autowired
    private AmCollectorSourceMapper amCollectorSourceMapper;


    @Override
    @Transactional(isolation = Isolation.DEFAULT, readOnly = true)
    public List<UserBean> getAllUser() {
        return userBeanMapper.selectByExample(null);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, readOnly = true)
    public List<AmCollectorSource> getAll() {
        return  amCollectorSourceMapper.selectByExample(null);
    }
}
