package com.wk.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class MonthSum implements Serializable{
    private Integer id;

    private String groupName;

    private Integer month;

    private Integer firstPersonCount;

    private Integer endPersonCount;

    private Integer depentsId;

}