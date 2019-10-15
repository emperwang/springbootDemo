package com.wk.bean.views;

import com.wk.bean.MonthSum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class MonthSumVo extends MonthSum implements Serializable{

    private String deptName;
}
