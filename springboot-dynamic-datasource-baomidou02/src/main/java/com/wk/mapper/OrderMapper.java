package com.wk.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.common.DBConstants;
import com.wk.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    @DS(value = DBConstants.DATASOURCE_SLAVE)
    Order selectById(@Param("id") Integer id);

    @DS(value = DBConstants.DATASOURCE_MASTER)
    int insert(Order order);
}
