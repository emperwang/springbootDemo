package com.wk.web.service;

import com.wk.bean.MonthSum;
import com.wk.bean.views.DataGradeView;
import com.wk.util.GroupExcelUtil;

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

    int addGroupFromExcel(GroupExcelUtil instance);

    List<MonthSum> selectByDeptId(Integer id);
}
