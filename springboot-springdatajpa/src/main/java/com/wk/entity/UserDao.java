package com.wk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
public class UserDao {
    /**
     * strategy: 设置使用数据库主键自增策略
     * generator: 设置插入完成后, 查询最后生成的ID 填充到该属性中
     */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "create_time", nullable = false)
    private Date createTime;
}
