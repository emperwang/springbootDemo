package com.stu.manage.controller;

import com.stu.manage.entity.CommonResult;
import com.stu.manage.entity.Role;
import com.stu.manage.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping(value = "deleteId/{id}")
    public ResponseEntity deleteRoleById(@PathVariable(name = "id") long id){
        int ct = roleService.deleteRoleById(id);
        return ct>0?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping(value = "deleteIds")
    public int deleteRoleByMultipleIds(@RequestParam(name = "ids",required = true) List<Long> ids){
        return roleService.deleteRolesByIds(ids);
    }


    @PutMapping(value = "updateById")
    public int updateRole(@RequestBody Role u){
        return roleService.updateRolesById(u);
    }


    @PostMapping(value = "save")
    public ResponseEntity saveRole(@RequestBody Role u){
        return roleService.saveRole(u)!=null?ResponseEntity.status(HttpStatus.CREATED).body(u):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }
}

