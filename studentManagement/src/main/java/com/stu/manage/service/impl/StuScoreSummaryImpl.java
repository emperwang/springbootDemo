package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entiry.StuScoreSummary;
import com.stu.manage.mapper.StuScoreSummaryMapper;
import com.stu.manage.service.IStuScoreSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:28
 * @Description
 */
@Service
public class StuScoreSummaryImpl extends ServiceImpl<StuScoreSummaryMapper, StuScoreSummary> implements IStuScoreSummaryService {

    private StuScoreSummaryMapper stuScoreSummaryMapper;

    @Override
    public List<StuScoreSummary> listAllSummary() {
        QueryWrapper<StuScoreSummary> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("ssid");
        return stuScoreSummaryMapper.selectList(wrapper);
    }

    @Override
    public StuScoreSummary getColumnByYear(String year) {
        QueryWrapper<StuScoreSummary> wrapper = new QueryWrapper<>();
        wrapper.eq("academic_year",year);
        List<StuScoreSummary> stuScoreSummaries = stuScoreSummaryMapper.selectList(wrapper);
        return stuScoreSummaries.size()>0?stuScoreSummaries.get(0):new StuScoreSummary();
    }


    @Autowired
    public void setStuScoreSummaryMapper(StuScoreSummaryMapper stuScoreSummaryMapper) {
        this.stuScoreSummaryMapper = stuScoreSummaryMapper;
    }
}
