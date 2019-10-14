package com.wk.bean.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
// 店铺
public class Shopbean implements Serializable {
    // 门店
    private String shopName;
    // 门店编号
    private String shopNum;
    // 门店负责人系统号
    private String shopPrincipalNum;
    // 门店负责人姓名
    private String shopPrincipalName;
    // 店组
    private String shopTeam;
    // 店组编号
    private String shopTeamNum;
    // 商圈经理系统号
    private String businessCircleManagerNum;
    // 商圈经理姓名
    private String businessCircleManagerName;
    // 担任商圈时间
    private String managerStartTime;
    // 商圈等级
    private String businessCircleLevel;
    // 直属S2系统号
    private String directlyNum;
    // 直属S2姓名
    private String directlyName;
    // 担任该职级日志
    private String startManagerTime;
    // 每月月初人数
    private Integer januaryStart;
    private Integer februayStart;
    private Integer marchStart;
    private Integer aprilStart;
    private Integer mayStart;
    private Integer juneStart;
    private Integer julyStart;
    private Integer augustStart;
    private Integer septemberStart;
    private Integer octoberStart;
    private Integer novemberStart;
    private Integer decemberStart;
    // 每月月末人数
    private Integer januaryEnd;
    private Integer februayEnd;
    private Integer marchEnd;
    private Integer aprilEnd;
    private Integer mayEnd;
    private Integer juneEnd;
    private Integer julyEnd;
    private Integer augustEnd;
    private Integer septemberEnd;
    private Integer octoberEnd;
    private Integer novemberEnd;
    private Integer decemberEnd;


}
