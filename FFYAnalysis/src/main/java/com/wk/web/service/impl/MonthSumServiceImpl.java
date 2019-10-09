package com.wk.web.service.impl;

import com.wk.bean.MonthSum;
import com.wk.web.mapper.MonthSumMapper;
import com.wk.web.service.MonthSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MonthSumServiceImpl implements MonthSumService {

    @Autowired
    private MonthSumMapper sumMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<MonthSum> findAll() {
        List<MonthSum> monthSums = sumMapper.selectByExample(null);
        return monthSums;
    }
}
