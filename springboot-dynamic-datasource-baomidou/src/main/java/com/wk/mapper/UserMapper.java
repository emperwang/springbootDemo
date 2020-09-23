package com.wk.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.common.DBConstants;
import com.wk.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@DS(value = DBConstants.DATASOURCE_USERS)
public interface UserMapper {
    User selectById(@Param("id") Integer id);
}
