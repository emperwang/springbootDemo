package com.stu.manage.utils;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Sparks
 * @Date: 2024/1/21 12:04
 * @Description
 */
public class TimeFormatUtil {
    public static final String format1 = "YYYY-MM-dd HH:mm:ss";
    public static final String format2 = "YYYY-MM-dd_HH_mm_ss";

    private static ThreadLocal<DateTimeFormatter> threadLocal = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
    private static ThreadLocal<DateTimeFormatter> suffix = ThreadLocal.withInitial(() -> DateTimeFormatter.ofPattern("YYYY-MM-dd_HH_mm_ss"));

    private static Map<String,ThreadLocal<DateTimeFormatter>> formats = new ConcurrentHashMap<>(2);

    static {
        formats.put(format1, threadLocal);
        formats.put(format2, suffix);
    }

    public static DateTimeFormatter getFormat(String format){
        if (formats.containsKey(format)) {
         return formats.get(format).get();
        }
        return threadLocal.get();
    }
}

