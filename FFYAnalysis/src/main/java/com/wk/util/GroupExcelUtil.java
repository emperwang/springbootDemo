package com.wk.util;

import com.wk.bean.bo.Depementbean;
import com.wk.bean.bo.GroupExcelReadbean;
import com.wk.bean.bo.Regionsbean;
import com.wk.bean.bo.Shopbean;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class GroupExcelUtil extends ExcelUtilImpl {

    private GroupExcelReadbean groupExcelReadbean = new GroupExcelReadbean();
    private static GroupExcelUtil Instance = new GroupExcelUtil();
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
            Regionsbean regionsbean = exportValueIntoRegionbean(values.subList(0,4));
            Depementbean depementbean = exportValueIntoDepementbean(values.subList(5,13));
            Shopbean shopbean = exportValueIntoShopbean(values.subList(14,values.size()),titleFields);


        }
    }

    /**
     *  把最终的数据封装到 groupExcelReadbean中
     */
    public void putRegionIntoExcelBeanData(Regionsbean regionsbean){
        groupExcelReadbean.getRegions().put(regionsbean.getRegionName(),regionsbean);
    }

    /**
     *  添加 大区到对应的大部
     * @param depementbean
     * @param shopbean
     */
    public void putShopIntoDepement(Depementbean depementbean,Shopbean shopbean){

    }

    /**
     *  添加 shop 到对应的大区
     * @param regionsbean
     * @param depementbean
     */
    public void putDepementIntoRegion(Regionsbean regionsbean,Depementbean depementbean){

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

        for (int i=13;i <= strings.size()-1;i++) {
            setShopbeanMonth(shopbean,titleFields);
        }

        return shopbean;
    }

    /**
     *  设置对应 月份的 人数
     * @param shopbean
     * @param titleFields
     */
    private void setShopbeanMonth(Shopbean shopbean, Map<Integer, String> titleFields) {
        
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
        return Instance;
    }
}
