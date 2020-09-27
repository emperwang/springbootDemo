package com.wk.Mapper;

import com.wk.entity.OrderDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDao {

    /**
     * 插入订单记录
     */
    @Insert("insert into orders(user_id,product_id,pay_amount) values (#{userId},#{productId},#{payAmount})")
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    int saveOrder(OrderDO order);
}







