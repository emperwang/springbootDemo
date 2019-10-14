package com.wk.web.service.impl;

import com.wk.bean.MonthSum;
import com.wk.bean.MonthSumExample;
import com.wk.bean.views.DataGradeView;
import com.wk.web.service.MonthSumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonthSumServiceImpl implements MonthSumService {
    private static Logger log = LoggerFactory.getLogger(MonthSumServiceImpl.class);
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

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public MonthSum selectById(Integer id) {
        MonthSum monthSum = sumMapper.selectByPrimaryKey(id);
        return monthSum;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int updateGroupInfo(MonthSum monthSum) {
        int count = sumMapper.updateByPrimaryKey(monthSum);
        return count;
    }

    /**
     *  查找 第 month月 人数第一次达到 personCount
     * @param month
     * @param personCount
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<MonthSum> searchFirstMonthSatisifyCount(Integer month, Integer personCount) {
        // 先查找 month月人数达到的小组
        MonthSumExample example = new MonthSumExample();
        MonthSumExample.Criteria criteria = example.createCriteria();
        criteria.andMonthEqualTo(month);
        criteria.andPersonCountGreaterThanOrEqualTo(personCount);
        List<MonthSum> monthSums = sumMapper.selectByExample(example);
        List<String> names = getNames(monthSums);
        List<Integer> ids = getIds(monthSums);
        if (log.isDebugEnabled()){
            log.debug("month 月满足的小组: {}",monthSums.toString());
            log.debug("month 月满足的小组 ids: {}",names.toString());
        }

        // 再查找month月达到条件的小组在 month-1月人数也满足的小组
        if (names != null && ids!=null && names!=null && names.size() > 0) {
            MonthSumExample example2 = new MonthSumExample();
            MonthSumExample.Criteria example2Criteria = example2.createCriteria();
            example2Criteria.andMonthEqualTo(month-1);
            example2Criteria.andPersonCountGreaterThanOrEqualTo(personCount);
            example2Criteria.andGroupNameIn(names);
            example2Criteria.andIdNotIn(ids);
            List<MonthSum> monthSums1 = sumMapper.selectByExample(example2);

            // 两个小组求差集,即是结果
            monthSums.removeAll(monthSums1);
        }
        DataGradeView<MonthSum> dataGradeView = new DataGradeView<>();
        dataGradeView.setRows(monthSums);
        dataGradeView.setTotal(monthSums.size());
        return dataGradeView;
    }

    private List<String> getNames(List<MonthSum> monthSums){
        List<String> ids = new ArrayList<>();
        if (monthSums != null && monthSums.size() > 0){
            monthSums.forEach(sum ->{
                String name = sum.getGroupName();
                ids.add(name);
            });
        }
        return ids;
    }

    private List<Integer> getIds(List<MonthSum> monthSums){
        List<Integer> ids = new ArrayList<>();
        if (monthSums != null && monthSums.size() > 0){
            monthSums.forEach(sum ->{
                Integer id = sum.getId();
                ids.add(id);
            });
        }
        return ids;
    }
}
