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

    int insertDepentmentFromExcel(Collection<Regionsbean> regionsbeans);

    List<Depentments> selectByName(String name);

    DataGradeView<DepentmentVo> getDataGride();
}
