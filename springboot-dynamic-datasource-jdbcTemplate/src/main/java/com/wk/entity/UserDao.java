package com.wk.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Data
public class UserDao {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;
}
