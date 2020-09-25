package com.wk.mapper;

import com.wk.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Order selectById(@Param("id") Integer id);

    int saveOrder(Order order);
}
