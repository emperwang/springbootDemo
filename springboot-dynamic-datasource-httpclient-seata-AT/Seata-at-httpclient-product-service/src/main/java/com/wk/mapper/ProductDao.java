package com.wk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductDao {

    @Select("select stock from product where id=#{productId}")
    Integer getStock(@Param("productId") Long productId);

    @Update("update product set stock=stock-#{amount} where id=#{productId} and stock >= #{amount}")
    int reduceStock(@Param("productId")Long productId, @Param("amount") int amount);
}
