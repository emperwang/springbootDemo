package com.wk.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthSum monthSum = (MonthSum) o;
        return Objects.equals(groupName, monthSum.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupName);
    }
}