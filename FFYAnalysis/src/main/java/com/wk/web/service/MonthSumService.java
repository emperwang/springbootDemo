package com.wk.web.service;

import com.wk.bean.MonthSum;
import com.wk.bean.views.DataGradeView;

import java.util.List;

public interface MonthSumService {

    List<MonthSum> findAll();

    DataGradeView<MonthSum> dataGradeList();

    int getTotal();

    int addGroup(MonthSum monthSum);

    int batchDeleteGroup(List<Integer> integers);

    MonthSum selectById(Integer id);

    int updateGroupInfo(MonthSum monthSum);

    DataGradeView<MonthSum> searchFirstMonthSatisifyCount(Integer month, Integer personCount);
}
