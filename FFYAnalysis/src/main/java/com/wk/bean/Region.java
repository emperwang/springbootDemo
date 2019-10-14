package com.wk.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@ToString
@Setter
@Getter
public class Region implements Serializable{
    private Integer id;

    private String name;

}