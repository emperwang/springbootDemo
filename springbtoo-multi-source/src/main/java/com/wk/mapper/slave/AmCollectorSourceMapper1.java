package com.wk.mapper.slave;

import com.wk.entity.slave.AmCollectorSource;
import com.wk.entity.slave.AmCollectorSourceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AmCollectorSourceMapper1 {
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