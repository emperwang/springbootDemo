package com.wk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateParseUtil {

    private static Map<String,SimpleDateFormat> kindsOfFormatter=new HashMap<>();

    public static Date parse(String data,String pattern){
        Date date = null;
        try {
            date = getFormat(pattern).parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String format(Date date,String pattern){
        return getFormat(pattern).format(date);
    }

    private static SimpleDateFormat getFormat(String pattern){
        SimpleDateFormat dateFormat = null;
        if (kindsOfFormatter.containsKey(pattern)){
            dateFormat = kindsOfFormatter.get(pattern);
        }else{
            dateFormat = new SimpleDateFormat(pattern);
            kindsOfFormatter.put(pattern,dateFormat);
        }
        return dateFormat;
    }
}
