package com.wk.web.service.impl;

import com.wk.bean.Depentments;
import com.wk.bean.Region;
import com.wk.bean.RegionExample;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.DataGradeView;
import com.wk.web.mapper.RegionMapper;
import com.wk.web.service.DepentmentService;
import com.wk.web.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;
    @Autowired
    private DepentmentService depentmentService;

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
     *  <name,id>
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public Map<Integer, String> nameToId() {
        Map<Integer, String> maps = new HashMap<>();
        List<Region> regions = regionMapper.selectByExample(null);
        if (regions != null && regions.size() > 0){
            for (Region region : regions) {
                maps.put(region.getId(),region.getName());
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
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public Region selectById(Integer id) {
        Region region = new Region();
        if (id == null){
            return region;
        }
        region = regionMapper.selectByPrimaryKey(id);
        return region;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = false)
    public int insertRecord(Region region) {
        int count = 0;
        if (region != null){
            count += regionMapper.insert(region);
        }
        return count;
    }

    /**
     *  查询datagride的数据
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<Region> getAllForDataGride() {
        DataGradeView<Region> datas = new DataGradeView<>();
        List<Region> regions = regionMapper.selectByExample(null);
        if (regions != null && regions.size() > 0){
            datas.setTotal(regions.size());
            datas.setRows(regions);
        }
        return datas;
    }

    /**
     *  删除，但是此记录被使用，则不进行删除
     * @param ids
     * @return 返回一些提示信息
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public Map<String, String> batchDeleteReturnMsg(List<Integer> ids) {
        Map<String,String> maps = new HashMap<>();
        Map<Integer, String> namesMaps = nameToId();
        if (ids != null && ids.size()>0){
            String msg = "";
            for (Integer id : ids) {
                if (checkIdIsInUser(id)){
                    String name = namesMaps.get(id);
                    msg += name + ",";
                    continue;
                }
                deleteByPrimaryKey(id);
            }
            if (!"".equals(msg)){
                msg = msg.substring(0,msg.length()-1);
                msg += " these regions is in use,can not delete";
                maps.put("message",msg);
            }
        }
        return maps;
    }

    /**
     *  根据主键删除
     * @param id
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int deleteByPrimaryKey(Integer id) {
        int count = 0;
        if (id != null){
            count += regionMapper.deleteByPrimaryKey(id);
            log.info("region delete records :{}",id);
        }
        return count;
    }

    /**
     *  查看id对应的记录是否被使用
     * @param id
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public boolean checkIdIsInUser(Integer id) {
        if (id != null){
            List<Depentments> depentments = depentmentService.selectByRegionId(id);
            if (depentments.size() > 0){
                return true;
            }
        }
        return false;
    }


}
