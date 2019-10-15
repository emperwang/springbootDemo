package com.wk.web.service;

import com.wk.bean.Region;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.ComboVo;
import com.wk.bean.views.DataGradeView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RegionService {

    Map<String,Integer> getAllRegion();

    Map<Integer,String> nameToId();

    int insertRegionFromExcel(Collection<Regionsbean> regionsbeans);

    List<Region> selectByName(String name);

    Region selectById(Integer id);

    int insertRecord(Region region);

    int insertRecord(String name);

    DataGradeView<Region> getAllForDataGride();

    Map<String,String> batchDeleteReturnMsg(List<Integer> ids);

    int deleteByPrimaryKey(Integer id);

    boolean checkIdIsInUser(Integer id);

    List<ComboVo> getRegionCombo();

    int updateRecord(Integer id, String name);
}
