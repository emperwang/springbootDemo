package com.wk.entity;

import lombok.Data;

import java.util.Date;


@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
}
