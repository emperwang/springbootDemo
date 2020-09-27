package com.wk.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountDao {
    /**
     *  获取账户余额
     * @param userId
     * @return
     */
    @Select("select balance from account where id=#{userId}")
    Integer getBalance(@Param("userId") Long userId);

    //@Update("update account set balance=balance-#{price} where id=#{id} and balance>=#{price}")
    @Update("update account set balance=balance-#{price} where id=1 and balance>=#{price}")
    int reduceBalance(@Param("id") Long userId,@Param("price") Integer price);
}
