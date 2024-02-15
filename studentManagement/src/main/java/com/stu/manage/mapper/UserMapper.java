package com.stu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 22:28
 * @Description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM stu_user")
    List<User> listUsers();



}
