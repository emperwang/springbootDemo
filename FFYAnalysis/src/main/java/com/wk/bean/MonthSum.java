package com.wk.bean;

import lombok.*;

import java.io.Serializable;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthSum implements Serializable{
    private Integer id;

    private String groupName;

    private Integer month;

    private Integer firstPersonCount;

    private Integer endPersonCount;

    private Integer depentsId;

    public MonthSum(String groupName, Integer month, Integer endPersonCount, Integer depentsId) {
        this.groupName = groupName;
        this.month = month;
        this.endPersonCount = endPersonCount;
        this.depentsId = depentsId;
    }

    public MonthSum(Integer id, String groupName, Integer month, Integer endPersonCount, Integer depentsId) {
        this.id = id;
        this.groupName = groupName;
        this.month = month;
        this.endPersonCount = endPersonCount;
        this.depentsId = depentsId;
    }
}