package com.wk.bean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
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
    private Integer totalNumberPerson;
    // 大部下辖的大区 <大部名字,<大区名,对应的大区>>
    private Map<String,Map<String,Depementbean>> depets = new HashMap<>();

    public int getTotalNumberPerson(String month) {
        if (month == null){
            int res = calcTotalNumberPerson(month);
            totalNumberPerson = res;
            return totalNumberPerson;
        }
        return totalNumberPerson;
    }

    private int calcTotalNumberPerson(String month){
        int result = 0 ;
        if (depets == null || depets.size() <=0 ||
                this.regionName == null || this.regionName.length()<=0){
            return result;
        }

        Map<String, Depementbean> depements = depets.get(this.getRegionName());
        if (depements == null || depements.size() <= 0){
            result = 0;
        }else {
            for (Depementbean depementbean : depements.values()){
                result += depementbean.getPersonCount(month);
            }
        }
        return result;
    }
}
