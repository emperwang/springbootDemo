package com.wk.mapper.mysql;

import com.wk.Entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> listAll();
}
