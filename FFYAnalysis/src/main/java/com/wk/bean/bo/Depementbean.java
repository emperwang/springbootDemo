package com.wk.bean.bo;

import com.wk.constant.MonthConstant;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private int personCount;

    // 下辖的店铺,<大区名,<店组名,对应的店组>
    private Map<String,Map<String,Shopbean>> shops = new HashMap<>();

    /**
     *  把下属店组人数相加得到总的大区人数
     * @return
     */
    public int calcPersonCount(String month){
        if (shops.size() <= 0 || depetName == null || depetName.length() <= 0){
            this.personCount = 0;
            return personCount;
        }

        Map<String, Shopbean> deptShops = shops.get(depetName);
        if (deptShops == null || deptShops.size() <= 0){
            this.personCount = 0;
            return personCount;
        }else{
            Collection<Shopbean> shopbeans = deptShops.values();
            for (Shopbean shopbean : shopbeans) {
                int count = getShopBeanPersonCount(shopbean,month);
                this.personCount += count;
            }
        }
        return personCount;
    }

    private int getShopBeanPersonCount(Shopbean shopbean, String month) {
        int count = 0;
        switch (month) {
            case MonthConstant.januaryStart:
                Integer januaryStart = shopbean.getJanuaryStart();
                if (januaryStart != null) {
                    count += januaryStart;
                }
                break;
            case MonthConstant.januaryEnd:
                Integer januaryEnd = shopbean.getJanuaryEnd();
                if (januaryEnd != null) {
                    count += januaryEnd;
                }
                break;
            case MonthConstant.februayStart:
                Integer februayStart = shopbean.getFebruayStart();
                if (februayStart != null) {
                    count += februayStart;
                }
                break;
            case MonthConstant.februayEnd:
                Integer februayEnd = shopbean.getFebruayEnd();
                if (februayEnd != null) {
                    count += februayEnd;
                }
                break;
            case MonthConstant.marchStart:
                Integer marchStart = shopbean.getMarchStart();
                if (marchStart != null) {
                    count += marchStart;
                }
                break;
            case MonthConstant.marchEnd:
                Integer marchEnd = shopbean.getMarchEnd();
                if (marchEnd != null) {
                    count += marchEnd;
                }
                break;
            case MonthConstant.aprilStart:
                Integer aprilStart = shopbean.getAprilStart();
                if (aprilStart != null) {
                    count += aprilStart;
                }
                break;
            case MonthConstant.aprilEnd:
                Integer aprilEnd = shopbean.getAprilEnd();
                if (aprilEnd != null) {
                    count += aprilEnd;
                }
                break;
            case MonthConstant.mayStart:
                Integer mayStart = shopbean.getMayStart();
                if (mayStart != null) {
                    count += mayStart;
                }
                break;
            case MonthConstant.mayEnd:
                Integer mayEnd = shopbean.getMayEnd();
                if (mayEnd != null) {
                    count += mayEnd;
                }
                break;
            case MonthConstant.julyStart:
                Integer julyStart = shopbean.getJulyStart();
                if (julyStart != null) {
                    count += julyStart;
                }
                break;
            case MonthConstant.julyEnd:
                Integer julyEnd = shopbean.getJulyEnd();
                if (julyEnd != null) {
                    count += julyEnd;
                }
                break;
            case MonthConstant.juneStart:
                Integer juneStart = shopbean.getJuneStart();
                if (juneStart != null) {
                    count += juneStart;
                }
                break;
            case MonthConstant.juneEnd:
                Integer juneEnd = shopbean.getJuneEnd();
                if (juneEnd != null) {
                    count += juneEnd;
                }
                break;
            case MonthConstant.augustStart:
                Integer augustStart = shopbean.getAugustStart();
                if (augustStart != null) {
                    count += augustStart;
                }
                break;
            case MonthConstant.augustEnd:
                Integer augustEnd = shopbean.getAugustEnd();
                if (augustEnd != null) {
                    count += augustEnd;
                }
                break;
            case MonthConstant.septemberStart:
                Integer septemberStart = shopbean.getSeptemberStart();
                if (septemberStart != null) {
                    count += septemberStart;
                }
                break;
            case MonthConstant.septemberEnd:
                Integer septemberEnd = shopbean.getSeptemberEnd();
                if (septemberEnd != null) {
                    count += septemberEnd;
                }
                break;
            case MonthConstant.octoberStart:
                Integer octoberStart = shopbean.getOctoberStart();
                if (octoberStart != null) {
                    count += octoberStart;
                }
                break;
            case MonthConstant.octoberEnd:
                Integer octoberEnd = shopbean.getOctoberEnd();
                if (octoberEnd != null) {
                    count += octoberEnd;
                }
                break;
            case MonthConstant.novemberStart:
                Integer novemberStart = shopbean.getNovemberStart();
                if (novemberStart != null) {
                    count += novemberStart;
                }
                break;
            case MonthConstant.novemberEnd:
                Integer novemberEnd = shopbean.getNovemberEnd();
                if (novemberEnd != null) {
                    count += novemberEnd;
                }
                break;
            case MonthConstant.decemberStart:
                Integer decemberStart = shopbean.getDecemberStart();
                if (decemberStart != null) {
                    count += decemberStart;
                }
                break;
            case MonthConstant.decemberEnd:
                Integer decemberEnd = shopbean.getDecemberEnd();
                if (decemberEnd != null) {
                    count += decemberEnd;
                }
                break;
            default:
                log.error(month + " invalid ");
                break;
        }
        return count;
    }
}
