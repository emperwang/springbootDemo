package com.wk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JSONUtil {
    private static Logger log = LoggerFactory.getLogger(JSONUtil.class);
    /**
     *  把javabean转换为json
     * @param object
     * @return
     */
    public static String beanToJson(Object object){
        return JSON.toJSONString(object);
    }

    /**
     *  把json转换为bean对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String jsonStr,Class<T> clazz){
        if (jsonStr != null && jsonStr.length()>0){
            T t = JSON.parseObject(jsonStr, clazz);
            return t;
        }
        return null;
    }

    /**
     *  把json数组转换为对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToBeanList(String jsonStr,Class<T> clazz){
        if (jsonStr != null && jsonStr.length()>0) {
            List<T> list = JSON.parseArray(jsonStr, clazz);
            return list;
        }
        return null;
    }

    /**
     *  获取JSON中的字段
     * @param jsonStr
     * @param field
     * @return
     */
    public static String getJsonField(String jsonStr, String field){
        if (jsonStr != null && jsonStr.length()>0 && field!=null && field.length()>0){
            JSONObject object = JSON.parseObject(jsonStr);
            if (object.containsKey(field)) {
                return object.get(field).toString();
            }else{
                log.error("getJsonField but the str :"+ jsonStr +"don't hava the field : "+field);
            }
        }
        return null;
    }
}
