package com.stu.manage.service;

import com.stu.manage.entiry.Role;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/16 22:14
 * @Description
 */
public interface IRoleService {

    List<Role> listRoles();

    int deleteRoleById(int id);

    int deleteRolesByIds(List<Integer> ids);

    int updateRolesById(Role r);

    int saveRole(Role r);
}
