package com.wk.demo.mapper.postgresql;

import com.wk.demo.entity.AmCollectorSource;
import com.wk.demo.entity.AmCollectorSourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AmCollectorSourceMapper {
    int countByExample(AmCollectorSourceExample example);

    int deleteByExample(AmCollectorSourceExample example);

    int deleteByPrimaryKey(String sourceId);

    int insert(AmCollectorSource record);

    int insertSelective(AmCollectorSource record);

    List<AmCollectorSource> selectByExample(AmCollectorSourceExample example);

    AmCollectorSource selectByPrimaryKey(String sourceId);

    int updateByExampleSelective(@Param("record") AmCollectorSource record, @Param("example") AmCollectorSourceExample example);

    int updateByExample(@Param("record") AmCollectorSource record, @Param("example") AmCollectorSourceExample example);

    int updateByPrimaryKeySelective(AmCollectorSource record);

    int updateByPrimaryKey(AmCollectorSource record);
}