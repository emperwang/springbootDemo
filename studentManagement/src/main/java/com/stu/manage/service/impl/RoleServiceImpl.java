package com.stu.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stu.manage.entity.Role;
import com.stu.manage.mapper.RoleMapper;
import com.stu.manage.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/16 22:14
 * @Description
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private RoleMapper roleMapper;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm:ss");

    @Override
    public List<Role> listRoles() {
        return roleMapper.listAllRoles();
    }

    @Override
    public int deleteRoleById(long id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public int deleteRolesByIds(List<Long> ids) {
        return roleMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateRolesById(Role r) {
        r.setUpdateTime(formatter.format(LocalDateTime.now()));
        return roleMapper.updateById(r);
    }

    @Override
    public Role saveRole(Role r) {
        r.setCreateTime(formatter.format(LocalDateTime.now()));
        r.setUpdateTime(formatter.format(LocalDateTime.now()));
        return roleMapper.insert(r)>0?r:null;
    }


    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
}
