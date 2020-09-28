package com.wk.entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAddDTO {
    private String username;
    private String password;
}
