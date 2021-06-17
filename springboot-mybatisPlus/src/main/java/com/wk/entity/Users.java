package com.wk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Sparks
 * @Date: 2021/6/17 21:54
 * @Description
 */
@Data
@Accessors(chain = true)
public class Users {
    @TableId(type = IdType.AUTO,value = "id")
    private Integer id;
    private String name;
    private Integer age;
}
