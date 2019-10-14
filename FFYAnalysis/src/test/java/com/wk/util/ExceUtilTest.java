package com.wk.util;

import com.wk.bean.bo.GroupExcelReadbean;
import org.junit.Test;

public class ExceUtilTest {

    @Test
    public void readTest(){
        String filePath = "F:\\FTPTest\\test.xlsx";
        GroupExcelUtil groupExcelUtil = GroupExcelUtil.getInstance();
        groupExcelUtil.readDataFromExcel(filePath);
        GroupExcelReadbean groupExcelReadbean = groupExcelUtil.getGroupExcelReadbean();
        System.out.println("**********************************************************************");
        System.out.println(groupExcelReadbean);
    }

    @Test
    public void testString(){
        String str = "运营副总\n姓名";
        System.out.println(str.replaceAll("\\n",""));
    }
}
