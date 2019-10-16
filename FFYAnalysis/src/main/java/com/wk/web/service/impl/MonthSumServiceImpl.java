package com.wk.web.service.impl;

import com.wk.bean.Depentments;
import com.wk.bean.MonthSum;
import com.wk.bean.MonthSumExample;
import com.wk.bean.bo.Depementbean;
import com.wk.bean.bo.GroupExcelReadbean;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.bo.Shopbean;
import com.wk.bean.views.DataGradeView;
import com.wk.bean.views.MonthSumVo;
import com.wk.util.GroupExcelUtil;
import com.wk.web.mapper.MonthSumMapper;
import com.wk.web.service.DepentmentService;
import com.wk.web.service.MonthSumService;
import com.wk.web.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class MonthSumServiceImpl implements MonthSumService {
    private static Logger log = LoggerFactory.getLogger(MonthSumServiceImpl.class);
    @Autowired
    private MonthSumMapper sumMapper;

    @Autowired
    private RegionService regionService;

    @Autowired
    private DepentmentService depentmentService;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<MonthSum> findAll() {
        List<MonthSum> monthSums = sumMapper.selectByExample(null);
        return monthSums;
    }

    /**
     *
     * @param page  第几页
     * @param rows  每页几行
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<MonthSumVo> dataGradeList(Integer page,Integer rows) {
        if (page == null){
            page = 1;
        }
        if (rows == null){
            rows = 1;
        }
        int currPage = page - 1;
        int start = currPage * rows;
        int end = page * rows;
        DataGradeView<MonthSumVo> dataGradeView = new DataGradeView<>();
        Map<Integer, String> deptMaps = depentmentService.idToName();
        List<MonthSumVo> lists = new ArrayList<>();
        List<MonthSum> all = findAll();

        if (all != null && all.size() > 0){
            for (MonthSum monthSum : all) {
                MonthSumVo monthSumVo = new MonthSumVo();
                BeanUtils.copyProperties(monthSum,monthSumVo);
                monthSumVo.setDeptName(deptMaps.get(monthSumVo.getDepentsId()));
                lists.add(monthSumVo);
            }
        }
        int size = lists.size();
        if (size >= 0){
            end = end > size?size:end;
        }
        dataGradeView.setTotal(lists.size());  // 此处是总页数
        dataGradeView.setRows(lists.subList(start,end));
        return dataGradeView;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public int getTotal() {
        int count = sumMapper.countByExample(null);
        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int addGroup(MonthSum monthSum) {
        int count = sumMapper.insert(monthSum);
        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int batchDeleteGroup(List<Integer> ids) {
        MonthSumExample example = new MonthSumExample();
        MonthSumExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        int count = sumMapper.deleteByExample(example);
        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public MonthSum selectById(Integer id) {
        MonthSum monthSum = sumMapper.selectByPrimaryKey(id);
        return monthSum;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int updateGroupInfo(MonthSum monthSum) {
        MonthSum monthSum1 = sumMapper.selectByPrimaryKey(monthSum.getId());
        monthSum.setFirstPersonCount(monthSum1.getFirstPersonCount());
        int count = sumMapper.updateByPrimaryKey(monthSum);
        return count;
    }

    /**
     *  查找 第 month月 人数第一次达到 personCount
     * @param month
     * @param personCount
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public DataGradeView<MonthSumVo> searchFirstMonthSatisifyCount(Integer month, Integer personCount,Integer page,Integer rows) {
        if (page == null){
            page = 1;
        }
        if (rows == null){
            rows = 1;
        }
        int currPage = page - 1;
        int start = currPage * rows;
        int end = page * rows;
        Map<Integer, String> deptMaps = depentmentService.idToName();
        // 先查找 month月人数达到的小组
        MonthSumExample example = new MonthSumExample();
        MonthSumExample.Criteria criteria = example.createCriteria();
        criteria.andMonthEqualTo(month);
        // criteria.and(personCount);
        List<MonthSum> monthSums = sumMapper.selectByExample(example);
        List<String> names = getNames(monthSums);
        List<Integer> ids = getIds(monthSums);
        if (log.isDebugEnabled()){
            log.debug("month 月满足的小组: {}",monthSums.toString());
            log.debug("month 月满足的小组 ids: {}",names.toString());
        }

        // 再查找month月达到条件的小组在 month-1月人数也满足的小组
        if (names != null && ids!=null && names!=null && names.size() > 0) {
            MonthSumExample example2 = new MonthSumExample();
            MonthSumExample.Criteria example2Criteria = example2.createCriteria();
            example2Criteria.andMonthEqualTo(month-1);
            // example2Criteria.andPersonCountGreaterThanOrEqualTo(personCount);
            example2Criteria.andGroupNameIn(names);
            example2Criteria.andIdNotIn(ids);
            List<MonthSum> monthSums1 = sumMapper.selectByExample(example2);

            // 两个小组求差集,即是结果
            monthSums.removeAll(monthSums1);
        }
        List<MonthSumVo> lists = new ArrayList<>();

        if (monthSums != null && monthSums.size() > 0){
            for (MonthSum monthSum : monthSums) {
                MonthSumVo monthSumVo = new MonthSumVo();
                BeanUtils.copyProperties(monthSum,monthSumVo);
                monthSumVo.setDeptName(deptMaps.get(monthSumVo.getDepentsId()));
                lists.add(monthSumVo);
            }
        }
        int size = lists.size();
        if (size >= 0){
            end = end > size?size:end;
        }
        DataGradeView<MonthSumVo> dataGradeView = new DataGradeView<>();
        dataGradeView.setRows(lists.subList(start,end));
        dataGradeView.setTotal(lists.size());
        return dataGradeView;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public int addGroupFromExcel(GroupExcelUtil instance) {
        int count = 0;
        GroupExcelReadbean groupExcelReadbean = instance.getGroupExcelReadbean();
        // insert region
        Collection<Regionsbean> regionsbeans = groupExcelReadbean.getRegions().values();
        // 插入大部和大区
        regionService.insertRegionFromExcel(regionsbeans);
        depentmentService.insertDepentmentFromExcel(regionsbeans);
        // 插入店组
        Map<String, Integer> regiodIds = regionService.getAllRegion();
        Map<String, Integer> deptIds = depentmentService.getAllDepentments();
        for (Regionsbean regionsbean : regionsbeans) {
            Integer regId = regiodIds.get(regionsbean.getRegionName());
            if (regId == null){
                log.error("region {} is not exist ,please check..",regionsbean.getRegionName());
                return 0;
            }
            Map<String, Depementbean> depementbeanMap = regionsbean.getDepets().get(regionsbean.getRegionName());
            if (depementbeanMap != null && depementbeanMap.size() > 0){
                Collection<Depementbean> values = depementbeanMap.values();
                for (Depementbean value : values) {
                    String depetName = value.getDepetName();
                    Integer deptId = deptIds.get(depetName);
                    if (deptId == null){
                        log.error("dept {} is not exist ,please check ..",depetName);
                        return 0;
                    }
                    Map<String, Shopbean> shopbeanMap = value.getShops().get(depetName);
                    if (shopbeanMap != null && shopbeanMap.size() > 0){
                        Collection<Shopbean> values1 = shopbeanMap.values();
                        for (Shopbean shopbean : values1) {
                            List<MonthSum> monthSums = convertShopToMonthSum(shopbean, deptId);
                            sumMapper.batchInsert(monthSums);
                        }
                    }
                }
            }
        }

        return count;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = true)
    public List<MonthSum> selectByDeptId(Integer id) {
        List<MonthSum> lists = new ArrayList<>();
        if (id != null){
            MonthSumExample example = new MonthSumExample();
            MonthSumExample.Criteria criteria = example.createCriteria();
            criteria.andDepentsIdEqualTo(id);

            lists.addAll(sumMapper.selectByExample(example));
        }
        return lists;
    }

    /**
     *  把读取到的shopbean 内容封装到 monthSum中
     * @param shopbean
     * @return
     */
    private List<MonthSum> convertShopToMonthSum(Shopbean shopbean,Integer deptId) {
        List<MonthSum> lists = new ArrayList<>();
        if (shopbean != null){
            String groupName = shopbean.getShopTeam();

            // 不同的月份代表一个实体bean
            if (shopbean.getJanuaryStart() != null || shopbean.getJanuaryEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(1);
                if (shopbean.getJanuaryStart() != null) {
                    monthSum.setFirstPersonCount(shopbean.getJanuaryStart());
                }
                if (shopbean.getJanuaryEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getJanuaryEnd());
                }
                lists.add(monthSum);
            }

            if (shopbean.getFebruayStart() != null || shopbean.getFebruayEnd() != null){
                Integer februayStart = shopbean.getFebruayStart();
                Integer februayEnd = shopbean.getFebruayEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(2);
                if (februayStart != null){
                    monthSum.setFirstPersonCount(februayStart);
                }
                if (shopbean.getFebruayEnd() != null){
                    monthSum.setEndPersonCount(februayEnd);
                }
                lists.add(monthSum);
            }

            if (shopbean.getMarchStart() != null || shopbean.getMarchEnd() != null){
                Integer marchStart = shopbean.getMarchStart();
                Integer marchEnd = shopbean.getMarchEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(3);
                if(marchStart != null) {
                    monthSum.setFirstPersonCount(marchStart);
                }
                if (marchEnd != null){
                    monthSum.setEndPersonCount(marchEnd);
                }
                lists.add(monthSum);
            }

            if (shopbean.getAprilStart() != null || shopbean.getAprilEnd() != null){
                Integer aprilStart = shopbean.getAprilStart();
                Integer aprilEnd = shopbean.getAprilEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(4);
                if (aprilStart != null) {
                    monthSum.setFirstPersonCount(shopbean.getAprilStart());
                }
                if (aprilEnd != null){
                    monthSum.setEndPersonCount(aprilEnd);
                }
                lists.add(monthSum);
            }

            if (shopbean.getMayStart() != null || shopbean.getMayEnd() != null){
                Integer mayStart = shopbean.getMayStart();
                Integer mayEnd = shopbean.getMayEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(5);
                if (mayStart != null) {
                    monthSum.setFirstPersonCount(mayStart);
                }
                if (mayEnd != null){
                    monthSum.setEndPersonCount(mayEnd);
                }
                lists.add(monthSum);
            }

            if (shopbean.getJuneStart() != null || shopbean.getJuneEnd() != null){
                Integer juneStart = shopbean.getJuneStart();
                Integer juneEnd = shopbean.getJuneEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(6);
                if (juneStart != null) {
                    monthSum.setFirstPersonCount(juneStart);
                }
                if (juneEnd != null){
                    monthSum.setEndPersonCount(juneEnd);
                }
                lists.add(monthSum);
            }

            if (shopbean.getJulyStart() != null || shopbean.getJulyEnd() != null){
                Integer julyStart = shopbean.getJulyStart();
                Integer julyEnd = shopbean.getJulyEnd();
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(7);
                monthSum.setFirstPersonCount(julyStart);
                monthSum.setEndPersonCount(julyEnd);
                lists.add(monthSum);
            }

            if (shopbean.getAugustStart() != null || shopbean.getAugustEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(8);
                monthSum.setFirstPersonCount(shopbean.getAugustStart());
                if (shopbean.getAugustEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getAugustEnd());
                }
                lists.add(monthSum);
            }
            if (shopbean.getSeptemberStart() != null || shopbean.getSeptemberEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(9);
                monthSum.setFirstPersonCount(shopbean.getSeptemberStart());
                if (shopbean.getSeptemberEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getSeptemberEnd());
                }
                lists.add(monthSum);
            }

            if (shopbean.getOctoberStart() != null || shopbean.getOctoberEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(10);
                monthSum.setFirstPersonCount(shopbean.getOctoberStart());
                if (shopbean.getOctoberEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getOctoberEnd());
                }
                lists.add(monthSum);
            }

            if (shopbean.getNovemberStart() != null || shopbean.getNovemberEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(11);
                monthSum.setFirstPersonCount(shopbean.getNovemberStart());
                if (shopbean.getNovemberEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getNovemberEnd());
                }
                lists.add(monthSum);
            }

            if (shopbean.getDecemberStart() != null || shopbean.getDecemberEnd() != null){
                MonthSum monthSum = new MonthSum();
                monthSum.setGroupName(groupName);
                monthSum.setDepentsId(deptId);
                monthSum.setMonth(12);
                monthSum.setFirstPersonCount(shopbean.getDecemberStart());
                if (shopbean.getDecemberEnd() != null){
                    monthSum.setEndPersonCount(shopbean.getDecemberEnd());
                }
                lists.add(monthSum);
            }
        }
        return lists;
    }

    private List<String> getNames(List<MonthSum> monthSums){
        List<String> ids = new ArrayList<>();
        if (monthSums != null && monthSums.size() > 0){
            monthSums.forEach(sum ->{
                String name = sum.getGroupName();
                ids.add(name);
            });
        }
        return ids;
    }

    private List<Integer> getIds(List<MonthSum> monthSums){
        List<Integer> ids = new ArrayList<>();
        if (monthSums != null && monthSums.size() > 0){
            monthSums.forEach(sum ->{
                Integer id = sum.getId();
                ids.add(id);
            });
        }
        return ids;
    }
}
