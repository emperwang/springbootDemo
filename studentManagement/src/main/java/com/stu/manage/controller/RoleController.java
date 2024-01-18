package com.stu.manage.controller;

import com.stu.manage.entiry.CommonResult;
import com.stu.manage.entiry.Role;
import com.stu.manage.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 23:18
 * @Description
 */
@RestController
@RequestMapping(value = "role")
public class RoleController {

    private IRoleService roleService;

    @GetMapping("list")
    public CommonResult listRoles(){
        List<Role> roles = roleService.listRoles();
        CommonResult.CommonResultBuilder<Role> builder = new CommonResult.CommonResultBuilder<>();
        return builder.counts(roles.size()).results(roles).build();
    }




    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
}

