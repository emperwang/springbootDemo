package com.wk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: Sparks
 * @Date: 2021/7/15 22:42
 * @Description
 */
@Setter
@Getter
@ToString
public class User {

    @NotNull
    @NotEmpty(message = "name must be given")
    private String name;
    @NotNull
    @Max(150)
    @Min(1)
    private Integer age;
    @NotNull
    private String address;
}
