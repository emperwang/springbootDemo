package com.stu.manage;

import com.stu.manage.utils.TimeFormatUtil;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author: Sparks
 * @Date: 2024/1/21 11:44
 * @Description
 */
public class LocationPathTest {

    @Test
    public void testPath(){
        String s = System.getProperty("user.dir");
        System.out.println(s);

        String s1 = UUID.randomUUID().toString().replace("-","");
        System.out.println(s1.substring(0,10));

        Path path = Paths.get(s,"/src/main/resources/images");
        System.out.println(path.normalize().toString());
    }

    @Test
    public void testTimeFormat(){
        DateTimeFormatter formatter = TimeFormatUtil.getFormat("YYYY-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String format = formatter.format(LocalDateTime.now());
        System.out.println(format);
        System.out.println(formatter2.format(LocalDateTime.now()));
    }
}
