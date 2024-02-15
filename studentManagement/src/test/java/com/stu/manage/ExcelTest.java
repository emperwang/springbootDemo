package com.stu.manage;

import com.alibaba.excel.EasyExcel;
import com.stu.manage.listener.ExcelEventListener;
import org.junit.Test;

/**
 * @author: Sparks
 * @Date: 2024/2/14 10:06
 * @Description
 */
public class ExcelTest {


    @Test
    public void testRead(){
        String path = ExcelTest.class.getClassLoader().getResource("20.xlsx").getFile();
        System.out.println(path);

        EasyExcel.read(path, new ExcelEventListener()).sheet(0).doRead();
    }


}
