package com.test.wk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JSONUtil {
    private static Logger log = LoggerFactory.getLogger(JSONUtil.class);

    public static String beanToJson(Object object){
        try {
            return JSON.toJSONString(object);
        }catch (Exception e){
            log.error("beanToJson exception:{}",ExUtil.buildErrorMessage(e));
        }
        return "";
    }

    public static <T> T jsonToBean(String jsonStr,Class<T> clazz){
        if (jsonStr != null && jsonStr.length()>0){
            try {
                T t = JSON.parseObject(jsonStr, clazz);
                return t;
            }catch (Exception e){
                log.error("jsonToBean exception, msg:{}",ExUtil.buildErrorMessage(e));
            }
        }
        return null;
    }

    public static <T> List<T> jsonToBeanList(String jsonStr,Class<T> clazz){
        if (jsonStr != null && jsonStr.length()>0) {
            try {
                List<T> list = JSON.parseArray(jsonStr, clazz);
                return list;
            }catch (Exception e){
                log.error("jsonToBeanList exception, msg:{}",ExUtil.buildErrorMessage(e));
            }
        }
        return null;
    }

    public static String getJsonField(String jsonStr, String field){
        if (jsonStr != null && jsonStr.length()>0 && field!=null && field.length()>0){
            try {
                JSONObject object = JSON.parseObject(jsonStr);
                if (object.containsKey(field)) {
                    return object.get(field)==null?"":object.get(field).toString();
                } else {
                    log.debug("getJsonField but the str :" + jsonStr + "don't hava the field : " + field);
                }
            }catch (Exception e){
                log.debug("getJsonField Exception, msg :{}",ExUtil.buildErrorMessage(e));
            }
        }
        return null;
    }

    public static String getJsonStrField(String jsonStr, String field){
        if (jsonStr != null && jsonStr.length()>0 && field!=null && field.length()>0){
            JSONObject object = JSON.parseObject(jsonStr);
            if (object.containsKey(field)) {
                return object.get(field)==null?"":object.get(field).toString();
            }
        }
        return null;
    }

    public static Integer getJsonIntField(String jsonStr, String field){
        if (jsonStr != null && jsonStr.length()>0 && field!=null && field.length()>0){
            try {
                JSONObject object = JSON.parseObject(jsonStr);
                if (object.containsKey(field)) {
                    return object.getInteger(field);
                } else {
                    log.debug("getJsonIntField but the str don't hava the field : " + field);
                }
            }catch (Exception e){
                log.debug("getJsonIntField Exception, msg :{}",ExUtil.buildErrorMessage(e));
            }
        }
        return null;
    }

    public static Long getJsonLongField(String jsonStr, String field){
        if (jsonStr != null && jsonStr.length()>0 && field!=null && field.length()>0){
            try {
                JSONObject object = JSON.parseObject(jsonStr);
                if (object.containsKey(field)) {
                    return object.getLong(field);
                } else {
                    log.debug("getJsonLongField but the str don't hava the field : " + field);
                }
            }catch (Exception e){
                log.debug("getJsonLongField Exception, msg :{}",ExUtil.buildErrorMessage(e));
            }
        }
        return null;
    }

    public static boolean isJson(String str){
        boolean flag = true;
        try {
            JSON.parseObject(str);
            flag = true;
        }catch (JSONException e){
            flag = false;
        }
        return flag;
    }
}
