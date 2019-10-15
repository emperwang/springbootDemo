package com.wk.util;

import com.wk.bean.bo.Depementbean;
import com.wk.bean.bo.GroupExcelReadbean;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.bo.Shopbean;
import com.wk.constant.MonthConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class GroupExcelUtil extends ExcelUtilImpl {

    private GroupExcelReadbean groupExcelReadbean = new GroupExcelReadbean();
    private GroupExcelUtil(){}

    /**
     * 0 - 4  regionbean
     * 5 - 13 depementbean
     * 14- values.size
     * @param values
     */
    @Override
    public void exportValueIntoBean(List<String> values,Map<Integer,String> titleFields) {
        if (values != null && values.size() > 0){
            log.info(values.toString());
            // Sublist包括开头,不包括结尾
            Regionsbean regionsbean = exportValueIntoRegionbean(values.subList(0,5));
            Depementbean depementbean = exportValueIntoDepementbean(values.subList(5,10));
            Shopbean shopbean = exportValueIntoShopbean(values.subList(10,values.size()-1),titleFields);
            putDataIntoBean(regionsbean,depementbean,shopbean);
        }
    }

    /**
     *  添加 大区到对应的大部
     * @param depementbean
     * @param shopbean
     */
    public void putDataIntoBean(Regionsbean regionsbean,Depementbean depementbean,Shopbean shopbean){
        putRegion(regionsbean,depementbean,shopbean);
        putDepement(regionsbean,depementbean,shopbean);
        putShop(regionsbean,depementbean,shopbean);
    }

    private void putShop(Regionsbean regionsbean,Depementbean depementbean,Shopbean shopbean){
        if (shopbean != null && shopbean.getShopTeam() != null && shopbean.getShopTeam().length() > 0){
            String regionName = regionsbean.getRegionName();
            String depetName = depementbean.getDepetName();
            String shopTeamName = shopbean.getShopTeam();
            Map<String, Regionsbean> regions = this.groupExcelReadbean.getRegions();
            if (regions.containsKey(regionName)){
                // 获取到 region对应的大区
                Map<String, Depementbean> depementbeanMap = regions.get(regionName).getDepets().get(regionName);
                if (depementbeanMap.containsKey(depetName)){
                    // 获取大区对应的店铺
                    Map<String, Shopbean> shopbeanMap = depementbeanMap.get(depetName).getShops().get(depetName);
                    if (shopbeanMap == null){
                        shopbeanMap = new HashMap<>();
                        shopbeanMap.put(shopTeamName,shopbean);
                        depementbeanMap.get(depetName).getShops().put(depetName,shopbeanMap);
                    }else if (!shopbeanMap.containsKey(shopTeamName)){
                        shopbeanMap.put(shopTeamName,shopbean);
                    }else{ // 已经包含，则什么也不做

                    }

                }else{
                    log.error("putShop but  "+ depetName +"  not exist");
                }
            }else{
                log.error("putShop but  "+regionName +"  not exist");
            }
        }
    }

    private void putDepement(Regionsbean regionsbean,Depementbean depementbean,Shopbean shopbean){
        if (depementbean != null && depementbean.getDepetName() != null && depementbean.getDepetName().length()>0){
            String regionName = regionsbean.getRegionName();
            String depetName = depementbean.getDepetName();
            if(this.groupExcelReadbean.getRegions().containsKey(regionName)){
                Regionsbean regionsbean1 = this.groupExcelReadbean.getRegions().get(regionName);
                Map<String, Depementbean> depementbeanMap = regionsbean1.getDepets().get(regionName);
                if (depementbeanMap == null){
                    depementbeanMap = new HashMap<>();
                    depementbeanMap.put(depetName,depementbean);
                    regionsbean1.getDepets().put(regionName,depementbeanMap);
                } else if (!depementbeanMap.containsKey(depetName)){
                    depementbeanMap.put(depetName,depementbean);
                }
            }else{
                log.error("putDepement but  "+regionName + " not exists");
            }
        }
    }

    private void putRegion(Regionsbean regionsbean,Depementbean depementbean,Shopbean shopbean){
        if (regionsbean != null && regionsbean.getRegionName() != null && regionsbean.getRegionName().length()>0) {
            String regionName = regionsbean.getRegionName();
            if (!this.groupExcelReadbean.getRegions().containsKey(regionName)) {
                this.groupExcelReadbean.getRegions().put(regionName, regionsbean);
            }
        }
    }

    /**
     *  封住数据
     * @param strings
     * @return
     */
    private Shopbean exportValueIntoShopbean(List<String> strings,Map<Integer,String> titleFields) {
        Shopbean shopbean = new Shopbean();
        if (strings == null || strings.size() <= 0){
            log.error("exportValueIntoShopbean parameter is invalid ,parame is :{}",strings.toString());
            return shopbean;
        }
        // 此处对应excel文件，进行的硬编码
        shopbean.setShopName(strings.get(0));
        shopbean.setShopNum(strings.get(1));
        shopbean.setShopPrincipalNum(strings.get(2));
        shopbean.setShopPrincipalName(strings.get(3));
        shopbean.setShopTeam(strings.get(4));
        shopbean.setShopTeamNum(strings.get(5));
        shopbean.setBusinessCircleManagerNum(strings.get(6));
        shopbean.setBusinessCircleManagerName(strings.get(7));
        shopbean.setManagerStartTime(strings.get(8));
        shopbean.setBusinessCircleLevel(strings.get(9));
        shopbean.setDirectlyNum(strings.get(10));
        shopbean.setDirectlyName(strings.get(11));
        shopbean.setStartManagerTime(strings.get(12)); // 27 到这里是第27列
        // 此处的 传递进入的 i 应该加上 之前的列,为9
        int size = strings.size();
        for (int i=13;i < size-1;i++) {
            setShopbeanMonth(shopbean,titleFields,strings.get(i),i+10);
        }
        return shopbean;
    }

    /**
     *  设置对应 月份的 人数
     * @param shopbean
     * @param titleFields
     */
    private void setShopbeanMonth(Shopbean shopbean, Map<Integer, String> titleFields,String value,int index) {
        String title = titleFields.get(index);
        log.info("title:{}",title);
        if (title == null || title.length() <= 0){
            log.error(title + "  is not a valid title");
            return;
        }
        if (value == null || "invalid char".equals(value)){
            value = "";
            return;
        }
        int personCount = Integer.parseInt(value);
        switch (title){
            case MonthConstant.januaryStart:
                shopbean.setJanuaryStart(personCount);
                break;
            case MonthConstant.januaryEnd:
                shopbean.setJanuaryEnd(personCount);
                break;
            case MonthConstant.februayStart:
                shopbean.setFebruayStart(personCount);
                break;
            case MonthConstant.februayEnd:
                shopbean.setFebruayEnd(personCount);
                break;
            case MonthConstant.marchStart:
                shopbean.setMarchStart(personCount);
                break;
            case MonthConstant.marchEnd:
                shopbean.setMarchEnd(personCount);
                break;
            case MonthConstant.aprilStart:
                shopbean.setAprilStart(personCount);
                break;
            case MonthConstant.aprilEnd:
                shopbean.setAprilEnd(personCount);
                break;
            case MonthConstant.mayStart:
                shopbean.setMayStart(personCount);
                break;
            case MonthConstant.mayEnd:
                shopbean.setMarchEnd(personCount);
                break;
            case MonthConstant.julyStart:
                shopbean.setJulyStart(personCount);
                break;
            case MonthConstant.julyEnd:
                shopbean.setJulyEnd(personCount);
                break;
            case MonthConstant.juneStart:
                shopbean.setJuneStart(personCount);
                break;
            case MonthConstant.juneEnd:
                shopbean.setJuneEnd(personCount);
                break;
            case MonthConstant.augustStart:
                shopbean.setAugustStart(personCount);
                break;
            case MonthConstant.augustEnd:
                shopbean.setAugustEnd(personCount);
                break;
            case MonthConstant.septemberStart:
                shopbean.setSeptemberStart(personCount);
                break;
            case MonthConstant.septemberEnd:
                shopbean.setSeptemberEnd(personCount);
                break;
            case MonthConstant.octoberStart:
                shopbean.setOctoberStart(personCount);
                break;
            case MonthConstant.octoberEnd:
                shopbean.setOctoberEnd(personCount);
                break;
            case MonthConstant.novemberStart:
                shopbean.setNovemberStart(personCount);
                break;
            case MonthConstant.novemberEnd:
                shopbean.setNovemberEnd(personCount);
                break;
            case MonthConstant.decemberStart:
                shopbean.setDecemberStart(personCount);
                break;
            case MonthConstant.decemberEnd:
                shopbean.setDecemberEnd(personCount);
                break;
            default:
                log.error(title + " invalid ");
                break;
        }
    }

    /**
     *  封装数据
     * @param strings
     * @return
     */
    private Depementbean exportValueIntoDepementbean(List<String> strings) {
        Depementbean depementbean = new Depementbean();
        if (strings == null || strings.size() <= 0 || strings.size() < 5){
            log.error("exportValueIntoDepementbean  parameter is invalid ,parame is :{}",strings.toString());
            return depementbean;
        }
        // 此处对应excel文件，进行的硬编码
        depementbean.setDepetName(strings.get(0));
        depementbean.setDepetNum(strings.get(1));
        depementbean.setDepetDirectorNum(strings.get(2));
        depementbean.setDepetDirectorName(strings.get(3));
        depementbean.setDepetDirectorStartTime(strings.get(4));

        return depementbean;

    }

    /**
     *  封装数据
     * @param strings
     * @return
     */
    private Regionsbean exportValueIntoRegionbean(List<String> strings) {
        Regionsbean regionsbean = new Regionsbean();
        if (strings == null || strings.size() <= 0 || strings.size() < 5){
            log.error("exportValueIntoRegionbean parameter is invalid ,parame is :{}",strings.toString());
            return regionsbean;
        }
        log.debug("exportValueIntoRegionbean parameter is :{}",strings.toString());
        // 此处对应excel文件，进行的硬编码
        regionsbean.setRegionName(strings.get(0));
        regionsbean.setRegionNum(strings.get(1));
        regionsbean.setOperationVicePresidentNum(strings.get(2));
        regionsbean.setOperationVicePresidentName(strings.get(3));
        regionsbean.setVicePresidentStartTime(strings.get(4));
        return regionsbean;
    }

    public static GroupExcelUtil getInstance() {
        return new GroupExcelUtil();
    }

    public void printBeanInfo(){
        log.info(this.groupExcelReadbean.toString());
    }
    // 打印总的总数信息
    public void printTotalNum(String month){
        Map<String, Regionsbean> regions = groupExcelReadbean.getRegions();
        Collection<Regionsbean> values = regions.values();
        for (Regionsbean value : values) {
            Map<String, Depementbean> depementbeanMap = value.getDepets().get(value.getRegionName());
            printDepetment(depementbeanMap,month);
            log.info(value.getRegionName()+" {} month  num is :{}",month,value.calcTotalNumberPerson(month));
        }
    }
    // 打印大区信息
    public void printDepetment(Map<String, Depementbean> depementbeanMap,String month){
        for (Depementbean depementbean : depementbeanMap.values()) {
            int personCount = depementbean.getPersonCount(month);
            log.info("depetment: {} ;personCount is: {} ,month is:{}",depementbean.getDepetName(),personCount,month);
        }
    }
}
