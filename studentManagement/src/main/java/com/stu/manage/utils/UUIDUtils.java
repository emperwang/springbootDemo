package com.stu.manage.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author: Sparks
 * @Date: 2024/1/21 12:22
 * @Description
 */
public class UUIDUtils {

    public static String getUUID(int start, int end){
        return generateUUID().substring(start,end);
    }

    public static String getUUID(){
        return generateUUID().substring(0,10);
    }

    private static String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String savePath(){
        Path path = Paths.get(System.getProperty("user.dir"), "studentManagement/src/main/resources/images");
        return path.normalize().toString();
    }
}
