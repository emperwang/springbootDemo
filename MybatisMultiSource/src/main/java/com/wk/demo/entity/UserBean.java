package com.wk.demo.entity;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBean {
    private Integer id;

    private String name;

    private Integer age;

    private String address;
}