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
// 大区
public class Depementbean implements Serializable {
    // 大区名字
    private String depetName;
    // 大区编号
    private String depetNum;
    // 大区总监系统号
    private String depetDirectorNum;
    // 大区总监姓名
    private String depetDirectorName;
    // 大区总监入职时间
    private String depetDirectorStartTime;
    // 大区对应的人数
    private Integer personCount;

    // 下辖的店铺,<大区名,对应的店铺>
    private Map<String,List<Shopbean>> shops = new HashMap<>();

}
