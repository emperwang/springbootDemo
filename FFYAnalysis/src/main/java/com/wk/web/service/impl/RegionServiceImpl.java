package com.wk.web.service.impl;

import com.wk.bean.Region;
import com.wk.bean.RegionExample;
import com.wk.bean.bo.Regionsbean;
import com.wk.web.mapper.RegionMapper;
import com.wk.web.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    /**
     *  查询所有大部的信息，并放入到map中 <id,name>
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public Map<String, Integer> getAllRegion() {
        Map<String, Integer> maps = new HashMap<>();
        List<Region> regions = regionMapper.selectByExample(null);
        if (regions != null && regions.size() > 0){
            for (Region region : regions) {
                maps.put(region.getName(),region.getId());
            }
        }
        return maps;
    }

    /**
     *  把从excel中读取的大部 插入表中
     * @param regionsbeans
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int insertRegionFromExcel(Collection<Regionsbean> regionsbeans) {
        int count = 0;
        if (regionsbeans != null && regionsbeans.size() > 0){
            for (Regionsbean regionsbean : regionsbeans) {
                String regionName = regionsbean.getRegionName();
                List<Region> regions = selectByName(regionName);
                if (regions.size() <= 0){
                    Region region = new Region();
                    region.setName(regionName);
                    insertRecord(region);
                }
            }
        }

        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<Region> selectByName(String name) {
        List<Region> regions = new ArrayList<>();
        if (name != null && name.length() > 0) {
            RegionExample example = new RegionExample();
            RegionExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(name);
            List<Region> res = regionMapper.selectByExample(example);
            regions.addAll(res);
        }
        return regions;
    }

    @Override
    public int insertRecord(Region region) {
        int count = 0;
        if (region != null){
            count += regionMapper.insert(region);
        }
        return count;
    }


}
