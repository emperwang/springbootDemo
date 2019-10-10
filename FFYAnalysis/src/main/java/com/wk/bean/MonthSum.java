package com.wk.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MonthSum implements Serializable{
    private Integer id;

    private String groupName;

    private Integer month;

    private Integer personCount;

    public MonthSum(){}

    public MonthSum(String groupName,Integer month,Integer personCount){
        this.groupName = groupName;
        this.month = month;
        this.personCount = personCount;
    }
}