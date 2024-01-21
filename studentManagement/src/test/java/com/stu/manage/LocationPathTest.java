package com.stu.manage;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
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
}
