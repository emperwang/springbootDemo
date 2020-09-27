package com.wk.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductDao {

    /**
     * 获取库存
     * @param productId
     * @return
     */
    @Select("select stock from product where id=#{productId}")
    Integer getStock(@Param("productId") Long productId);

    /**
     * 扣减库存
     * @param productId  商品编号
     * @param amount 扣减数量
     * @return 影响记录行数
     */
    @Update("update product set stock=stock-#{amount} where id=#{productId} and stock>=#{amount}")
    int reduceStock(@Param("productId")Long productId, @Param("amount") Integer amount);

}
