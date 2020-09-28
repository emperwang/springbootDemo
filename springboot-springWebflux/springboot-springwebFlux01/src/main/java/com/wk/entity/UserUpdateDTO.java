package com.wk.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private Integer id;
    private String username;
    private String password;
}
