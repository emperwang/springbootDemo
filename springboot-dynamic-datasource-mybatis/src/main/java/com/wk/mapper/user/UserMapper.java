package com.wk.mapper.user;

import com.wk.entity.Order;
import com.wk.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectById(@Param("id") int id);
}
