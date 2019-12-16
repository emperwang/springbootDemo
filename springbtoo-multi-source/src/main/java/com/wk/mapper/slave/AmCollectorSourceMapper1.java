package com.wk.mapper.slave;

import com.wk.entity.slave.AmCollectorSource;
import com.wk.entity.slave.AmCollectorSourceExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AmCollectorSourceMapper1 {
    int countByExample(AmCollectorSourceExample example);

    int deleteByExample(AmCollectorSourceExample example);

    int deleteByPrimaryKey(String sourceId);

    int insert(AmCollectorSource record);

    int insertSelective(AmCollectorSource record);

    List<AmCollectorSource> selectByExample(AmCollectorSourceExample example);
    /**
     * descripiton:
     *   mysql 分页查询
     * @author: wk
     * @params: offset : 偏移；  limits: 设置总数
     * @returns:
     * @time: 16:47
     * @modifier:
     * @since:
     */
    List<AmCollectorSource> selectPages(@Param("offset") Integer offset, @Param("limits") Integer limits);
    /**
     * descripiton:
     * 分页查询 使用map传参
     * @author: wk
     * @params:
     * @returns:
     * @time: 16:49
     * @modifier:
     * @since:
     */
    List<AmCollectorSource> selectPagesWithMap(Map<String,Object> map);

    AmCollectorSource selectByPrimaryKey(String sourceId);

    int updateByExampleSelective(@Param("record") AmCollectorSource record, @Param("example") AmCollectorSourceExample example);

    int updateByExample(@Param("record") AmCollectorSource record, @Param("example") AmCollectorSourceExample example);

    int updateByPrimaryKeySelective(AmCollectorSource record);

    int updateByPrimaryKey(AmCollectorSource record);
}