package com.wk.mapper.order;

import com.wk.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    Order selectById(@Param("id") int id);
}
