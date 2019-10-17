package com.wk.web.service;

import com.wk.bean.MonthSum;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.MonthSumVo;
import com.wk.util.GroupExcelUtil;

import java.util.List;

public interface MonthSumService {

    List<MonthSum> findAll();

    DataGradeView<MonthSumVo> dataGradeList(Integer page,Integer rows);

    int getTotal();

    int addGroup(MonthSum monthSum);

    int batchDeleteGroup(List<Integer> integers);

    MonthSum selectById(Integer id);

    int updateGroupInfo(MonthSum monthSum);

    DataGradeView<MonthSumVo> searchFirstMonthSatisifyCount(Integer month, Integer personCount,Integer page,Integer rows);

    int addGroupFromExcel(GroupExcelUtil instance);

    List<MonthSum> selectByDeptId(Integer id);

    int batchAddAndUpdate(String groups);
}
