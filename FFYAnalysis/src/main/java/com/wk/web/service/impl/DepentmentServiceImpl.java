package com.wk.web.service.impl;

import com.wk.bean.Depentments;
import com.wk.bean.DepentmentsExample;
import com.wk.bean.bo.Depementbean;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.DepentmentVo;
import com.wk.web.mapper.DepentmentsMapper;
import com.wk.web.service.DepentmentService;
import com.wk.web.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class DepentmentServiceImpl implements DepentmentService{

    @Autowired
    private DepentmentsMapper depentmentsMapper;
    @Autowired
    private RegionService regionService;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public Map<String,Integer> getAllDepentments() {
        Map<String,Integer> maps = new HashMap<>();
        List<Depentments> depentments = depentmentsMapper.selectByExample(null);
        if (depentments != null && depentments.size() > 0){
            for (Depentments depentment : depentments) {
                maps.put(depentment.getDeptName(),depentment.getId());
            }
        }
        return maps;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int insertDepentmentFromExcel(Collection<Regionsbean> regionsbeans) {
        int count = 0;
        Map<String,Integer> regionMap = regionService.getAllRegion();
        if (regionsbeans != null && regionsbeans.size() > 0){
            for (Regionsbean regionsbean : regionsbeans) {
                String regionName = regionsbean.getRegionName();
                // 获取大部id号
                Integer regId = regionMap.get(regionName);
                if (regId == null){
                    log.error("region {}  not exist,please check...",regionName);
                    return 0;
                }
                Map<String, Depementbean> depementbeanMap = regionsbean.getDepets().get(regionName);
                // 插入大区信息
                if (depementbeanMap != null && depementbeanMap.size() > 0){
                    Collection<Depementbean> values = depementbeanMap.values();
                    for (Depementbean value : values) {
                        String depetName = value.getDepetName();
                        List<Depentments> depentments = selectByName(depetName);
                        if (depentments.size() > 0){
                            // 已经有数据 ,则什么也不做
                        }else {
                            Depentments dep = new Depentments();
                            dep.setDeptName(depetName);
                            dep.setRegionId(regId);
                            count += depentmentsMapper.insert(dep);
                        }
                    }
                }
            }
        }

        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<Depentments> selectByName(String name) {
        List<Depentments> lists = new ArrayList<>();
        if (name != null && name.length() > 0){
            DepentmentsExample example = new DepentmentsExample();
            DepentmentsExample.Criteria criteria = example.createCriteria();
            criteria.andDeptNameEqualTo(name);
            lists.addAll(depentmentsMapper.selectByExample(example));
        }
        return lists;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<DepentmentVo> getDataGride() {
        DataGradeView<DepentmentVo> dataGradeView = new DataGradeView<>();
        List<DepentmentVo> lists=  new ArrayList<>();

        Map<Integer, String> regMap = regionService.nameToId();
        List<Depentments> depentments = depentmentsMapper.selectByExample(null);
        if (depentments != null && depentments.size() > 0){
            for (Depentments depentment : depentments) {
                DepentmentVo depentmentVo = new DepentmentVo();
                BeanUtils.copyProperties(depentment,depentmentVo);
                depentmentVo.setRegionName(regMap.get(depentmentVo.getRegionId()));
                lists.add(depentmentVo);
            }
        }
        dataGradeView.setTotal(lists.size());
        dataGradeView.setRows(lists);
        return dataGradeView;
    }
}
