package com.wk.web.service;

import com.wk.bean.Depentments;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.DepentmentVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepentmentService {

    Map<String,Integer> getAllDepentments();

    Map<Integer,String> idToName();

    int insertDepentmentFromExcel(Collection<Regionsbean> regionsbeans);

    List<Depentments> selectByName(String name);

    DepentmentVo selectByid(Integer id);

    DataGradeView<DepentmentVo> getDataGride();

    List<Depentments> selectByRegionId(Integer id);

    Map<String,String> batchDeleteReturnMsg(List<Integer> ids);

    int deleteByPrimaryKey(Integer id);

    boolean checkIdIsInUser(Integer id);

    int insertRecord(String deptName, Integer regionId);

    int updateRecord(Integer id, String deptName, Integer regionId);
}
