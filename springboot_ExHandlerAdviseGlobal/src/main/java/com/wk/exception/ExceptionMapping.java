package com.wk.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExceptionMapping extends AbstructConfigUtil {
    /**
     *  创建一个实例
     */
    private static final ExceptionMapping INSTANCE = new ExceptionMapping();
    /**
     *  文件名字
     */
    private String propertiesFileName = "error_code_msg_mapping.properties";

    private Map<String,String> errorMsgMap = new HashMap<>();

    private ExceptionMapping(){}

    @Override
    public String getPropertiesFilePath() {
        String path = System.getProperty("error.config.path");
        log.info("get class path is:"+path);
        return path;
    }

    @Override
    public String getPropertiesFileName() {
        return propertiesFileName;
    }

    public static ExceptionMapping getInstance(){
        return INSTANCE;
    }

    public String getExceptionMsg(Integer errorCode){
        return getStringValue(errorCode.toString());
    }
}
