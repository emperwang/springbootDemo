package com.wk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao {
    @Select("select balance from account where id=1")
    Integer getBalance(@Param("userId")Long userId);

    @Update("update account set balance=balance-#{price} where id=1 and balance>=#{price}")
    int reduceBalance(@Param("price") Integer price);
}
