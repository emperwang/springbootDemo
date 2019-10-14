package com.wk.bean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *  封装从excel中读取的数据
 */
@ToString
@Setter
@Getter
// 封装读取的excel数据
public class GroupExcelReadbean implements Serializable{
    // <大部名字,对应的大部>
    private Map<String,Regionsbean> regions = new HashMap<>();
}
