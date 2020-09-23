package com.wk.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.common.DBConstants;
import com.wk.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@DS(value = DBConstants.DATASOURCE_ORDERS)
public interface OrderMapper {
    Order selectById(@Param("id") Integer id);
}
