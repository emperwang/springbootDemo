package com.test.wk.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.wk.entity.Role;
import com.test.wk.entity.User;
import com.test.wk.service.UserService;
import com.test.wk.utils.EncryptUtil;
import com.test.wk.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;


@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping(value = "user/create", consumes = {"application/json"})
    public ResponseEntity createUser(@RequestBody User user){
        logger.info("createUser receive info : {}", user.toString());
        if (userService.addUser(user)){
            return ResponseUtil.getResponse(HttpStatus.CREATED);
        }
        return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "The user already exists.");
    }

    @DeleteMapping(value = "user/delete",consumes = {"application/json"})
    public ResponseEntity deleteUser(@RequestBody User user){
        logger.info("deleteUser receive info : {}", user.toString());
        if (userService.deleteUser(user)){
            return ResponseUtil.getResponse(HttpStatus.CREATED);
        }
        return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "The user doesn't exists.");
    }

    @PostMapping(value = "role/create",consumes = {"application/json"})
    public ResponseEntity createRole(@RequestBody Role role){
        logger.info("createRole receive info : {}", role.toString());
        if (userService.addRole(role)){
            return ResponseUtil.getResponse(HttpStatus.CREATED);
        }
        return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "The role already exists.");
    }

    @PutMapping(value = "user/assign",consumes = {"application/json"})
    public ResponseEntity assignRole(@RequestBody String infos) throws UnsupportedEncodingException {
        JSONObject object = JSONObject.parseObject(infos);
        String pwd = object.getString("password");
        object.put("password", EncryptUtil.encryptBase64(pwd));
        logger.info("assignRole receive info : {}", object.toJSONString());
        userService.assignRoleToUser(object.getString("userName"),pwd,object.getString("roleName"));
        return ResponseUtil.getResponse(HttpStatus.CREATED);
    }

    @PostMapping(value = "auth",consumes = {"application/json"})
    public ResponseEntity auth(@RequestBody User user) throws UnsupportedEncodingException {
        logger.info("auth receive info : {}",user.toString());
        String token = userService.authenticate(user);
        if (token != null && !StringUtils.isEmpty(token)) {
            return ResponseUtil.getResponse(HttpStatus.OK, token);
        }
        return ResponseUtil.getResponse(HttpStatus.BAD_REQUEST, "Invalid user.");
    }
}
