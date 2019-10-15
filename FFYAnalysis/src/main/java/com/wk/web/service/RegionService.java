package com.wk.web.service;

import com.wk.bean.Region;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.DataGradeView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RegionService {

    Map<String,Integer> getAllRegion();

    Map<Integer,String> nameToId();

    int insertRegionFromExcel(Collection<Regionsbean> regionsbeans);

    List<Region> selectByName(String name);

    int insertRecord(Region region);

    DataGradeView<Region> getAllForDataGride();

    Map<String,String> batchDeleteReturnMsg(List<Integer> ids);

    int deleteByPrimaryKey(Integer id);

    boolean checkIdIsInUser(Integer id);
}
