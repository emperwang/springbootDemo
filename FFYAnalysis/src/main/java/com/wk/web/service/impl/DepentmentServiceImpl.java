package com.wk.web.service.impl;

import com.wk.bean.Depentments;
import com.wk.bean.DepentmentsExample;
import com.wk.bean.MonthSum;
import com.wk.bean.bo.Depementbean;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.views.ComboVo;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.DepentmentVo;
import com.wk.web.mapper.DepentmentsMapper;
import com.wk.web.service.DepentmentService;
import com.wk.web.service.MonthSumService;
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
    @Autowired
    private MonthSumService monthSumService;

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
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public Map<Integer, String> idToName() {
        Map<Integer, String> maps = new HashMap<>();
        List<Depentments> depentments = depentmentsMapper.selectByExample(null);
        if (depentments != null && depentments.size() > 0){
            for (Depentments depentment : depentments) {
                maps.put(depentment.getId(),depentment.getDeptName());
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
    public DepentmentVo selectByid(Integer id) {
        DepentmentVo depentments = new DepentmentVo();
        if (id != null){
            Map<Integer, String> regionsMap = regionService.nameToId();
            Depentments depts = depentmentsMapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(depts,depentments);
            depentments.setRegionName(regionsMap.get(depts.getRegionId()));
        }
        return depentments;
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

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<Depentments> selectByRegionId(Integer id) {
        List<Depentments> lists = new ArrayList<>();
        if (id != null){
            DepentmentsExample example = new DepentmentsExample();
            DepentmentsExample.Criteria criteria = example.createCriteria();
            criteria.andRegionIdEqualTo(id);

            lists.addAll(depentmentsMapper.selectByExample(example));
        }
        return lists;
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
        Map<Integer, String> namesMap = idToName();
        if (ids != null && ids.size()>0){
            String msg = "";
            for (Integer id : ids) {
                if (checkIdIsInUser(id)){
                    String name = namesMap.get(id);
                    msg += name + ",";
                    continue;
                }
                deleteByPrimaryKey(id);
            }
            if (!"".equals(msg)){
                msg = msg.substring(0,msg.length()-1);
                msg += " these depts is in use,can not delete";
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
            count += depentmentsMapper.deleteByPrimaryKey(id);
            log.info("depement delete records :{}",id);
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
            List<MonthSum> monthSums = monthSumService.selectByDeptId(id);
            if (monthSums.size() > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int insertRecord(String deptName, Integer regionId) {
        int count = 0 ;

        if (deptName != null && regionId != null && deptName.length() > 0){
            Depentments depentments = new Depentments();
            depentments.setRegionId(regionId);
            depentments.setDeptName(deptName);
            count += depentmentsMapper.insert(depentments);
        }

        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int updateRecord(Integer id, String deptName, Integer regionId) {
        int count = 0 ;

        if (id != null && deptName != null && regionId != null && deptName.length() > 0){
            Depentments depentments = new Depentments();
            depentments.setRegionId(regionId);
            depentments.setDeptName(deptName);
            depentments.setId(id);
            count += depentmentsMapper.updateByPrimaryKey(depentments);
        }

        return count;
    }

    @Override
    public List<ComboVo> getDeptCombo() {
        List<ComboVo> lists = new ArrayList<>();

        List<Depentments> depentments = depentmentsMapper.selectByExample(null);
        if (depentments != null && depentments.size() > 0){
            for (Depentments depentment : depentments) {
                ComboVo comboVo = new ComboVo();
                comboVo.setText(depentment.getDeptName());
                comboVo.setId(depentment.getId());
                lists.add(comboVo);
            }
        }


        return lists;
    }
}
