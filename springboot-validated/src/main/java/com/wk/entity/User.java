package com.wk.entity;

import com.wk.validation.Contains;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Constraint;
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
    @Max(message = "age 不能超过150", value = 150)
    @Min(message = "age 不能小于 1", value = 1)
    private Integer age;

    @Contains(required = true, addr = {"bj","nj"})
    private String address;
}
