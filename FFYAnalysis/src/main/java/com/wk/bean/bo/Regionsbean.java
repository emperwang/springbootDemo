package com.wk.bean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Setter
@Getter
// 对应大部
public class Regionsbean implements Serializable {
    // 大部名字
    private String regionName;
    // 大部编号
    private String regionNum;
    // 大部运营副总系统号
    private String operationVicePresidentNum;
    // 运行副总姓名
    private String  operationVicePresidentName;
    // 副总入职时间
    private String vicePresidentStartTime;
    // 大部总人数
    private int totalNumberPerson;
    // 大部下辖的大区 <大部名字,对应的大区>
    private Map<String,List<Depementbean>> depets = new HashMap<>();
}
