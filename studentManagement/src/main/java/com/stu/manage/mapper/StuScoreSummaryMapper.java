package com.stu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.manage.entity.StuScoreSummary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:27
 * @Description
 */
@Mapper
public interface StuScoreSummaryMapper extends BaseMapper<StuScoreSummary> {


    void batchInsert(List<StuScoreSummary> summaryList);
}
