package com.wk.web.service;

import com.wk.bean.Region;
import com.wk.bean.bo.Regionsbean;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface RegionService {

    Map<String,Integer> getAllRegion();

    int insertRegionFromExcel(Collection<Regionsbean> regionsbeans);

    List<Region> selectByName(String name);

    int insertRecord(Region region);
}
