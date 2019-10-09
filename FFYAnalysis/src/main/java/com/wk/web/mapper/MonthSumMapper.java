package com.wk.web.mapper;

import com.wk.bean.MonthSum;
import com.wk.bean.MonthSumExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MonthSumMapper {
    int countByExample(MonthSumExample example);

    int deleteByExample(MonthSumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonthSum record);

    int insertSelective(MonthSum record);

    List<MonthSum> selectByExample(MonthSumExample example);

    MonthSum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonthSum record, @Param("example") MonthSumExample example);

    int updateByExample(@Param("record") MonthSum record, @Param("example") MonthSumExample example);

    int updateByPrimaryKeySelective(MonthSum record);

    int updateByPrimaryKey(MonthSum record);
}