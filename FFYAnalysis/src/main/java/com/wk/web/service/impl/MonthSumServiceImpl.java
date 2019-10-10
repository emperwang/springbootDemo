package com.wk.web.service.impl;

import com.wk.bean.MonthSum;
import com.wk.bean.MonthSumExample;
import com.wk.bean.views.DataGradeView;
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

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<MonthSum> dataGradeList() {
        DataGradeView<MonthSum> dataGradeView = new DataGradeView<>();
        int total = getTotal();
        List<MonthSum> all = findAll();
        dataGradeView.setTotal(total);
        dataGradeView.setRows(all);
        return dataGradeView;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public int getTotal() {
        int count = sumMapper.countByExample(null);
        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int addGroup(MonthSum monthSum) {
        int count = sumMapper.insert(monthSum);
        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int batchDeleteGroup(List<Integer> ids) {
        MonthSumExample example = new MonthSumExample();
        MonthSumExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        int count = sumMapper.deleteByExample(example);
        return count;
    }
}
