package com.wk.util;

import com.wk.bean.bo.GroupExcelReadbean;
import com.wk.constant.MonthConstant;
import org.junit.Test;

public class ExceUtilTest {

    @Test
    public void readTest(){
        String filePath = "H:\\ffy-excel\\eexcel\\test.xlsx";
        GroupExcelUtil groupExcelUtil = GroupExcelUtil.getInstance();
        groupExcelUtil.readDataFromExcel(filePath);
        GroupExcelReadbean groupExcelReadbean = groupExcelUtil.getGroupExcelReadbean();
        System.out.println("**********************************************************************");
        System.out.println(groupExcelReadbean);
        System.out.println("*************************** count *******************************************");
        /*Map<String, Regionsbean> regions = groupExcelReadbean.getRegions();
        String regionsName = "广州东大部";
        Regionsbean regionsbean = regions.get(regionsName);
        Map<String, Map<String, Depementbean>> depets = regionsbean.getDepets();
        Map<String, Depementbean> depementbeanMap = depets.get(regionsName);
        for (Depementbean depementbean : depementbeanMap.values()) {
            int personCount = depementbean.calcPersonCount(MonthConstant.juneEnd);
            System.out.println("depetment: "+depementbean.getDepetName()+";  personCount is :"+personCount);
        }*/

        groupExcelUtil.printTotalNum(MonthConstant.julyEnd);
    }

    @Test
    public void testString(){
        String str = "运营副总\n姓名";
        System.out.println(str.replaceAll("\\n",""));
    }
}
