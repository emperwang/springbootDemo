package com.stu.manage.controller;

import com.stu.manage.entiry.CommonResult;
import com.stu.manage.entiry.User;
import com.stu.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Sparks
 * @Date: 2024/1/15 21:41
 * @Description
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "list")
    public CommonResult<User> getUser(){
        List<User> users = userService.listUsers();
        CommonResult.CommonResultBuilder<User> builder = new CommonResult.CommonResultBuilder();
        return builder.counts(users.size()).results(users).build();
    }


    @DeleteMapping(value = "deleteId/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name = "id") long id){
        int ct = userService.deleteUserById(id);
        return ct>0?ResponseEntity.status(HttpStatus.NO_CONTENT).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping(value = "deleteIds")
    public int deleteUserByMultipleIds(@RequestParam(name = "ids",required = true) List<Long> ids){
        return userService.deleteByUids(ids);
    }


    @PutMapping(value = "updateById")
    public int updateUser(@RequestBody User u){
        return userService.updateUserById(u);
    }


    @PostMapping(value = "save")
    public ResponseEntity saveUser(@RequestBody User u){
        return userService.saveUser(u)>0?ResponseEntity.status(HttpStatus.CREATED).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

}
