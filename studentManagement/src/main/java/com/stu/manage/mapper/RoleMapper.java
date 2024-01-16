package com.stu.manage.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.manage.entiry.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/16 22:27
 * @Description
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from stu_role;")
    List<Role> listAllRoles();

}
