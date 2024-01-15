package com.stu.manage.mapper;

import com.stu.manage.entiry.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 22:28
 * @Description
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM stu_user")
    List<User> listUsers();
}
