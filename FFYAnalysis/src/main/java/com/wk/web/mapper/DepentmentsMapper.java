package com.wk.web.mapper;

import com.wk.bean.Depentments;
import com.wk.bean.DepentmentsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepentmentsMapper {
    int countByExample(DepentmentsExample example);

    int deleteByExample(DepentmentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Depentments record);

    int insertSelective(Depentments record);

    List<Depentments> selectByExample(DepentmentsExample example);

    Depentments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Depentments record, @Param("example") DepentmentsExample example);

    int updateByExample(@Param("record") Depentments record, @Param("example") DepentmentsExample example);

    int updateByPrimaryKeySelective(Depentments record);

    int updateByPrimaryKey(Depentments record);
}