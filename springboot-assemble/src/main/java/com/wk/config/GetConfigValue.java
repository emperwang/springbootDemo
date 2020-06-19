package com.wk.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetConfigValue {
    private Logger log = LoggerFactory.getLogger(GetConfigValue.class);
    private static final GetConfigValue Instance = new GetConfigValue();
    private Properties properties = new Properties();
    private static String name = "/config.properties";

    private GetConfigValue(){
        try {
            initConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getPropertiesPath(){
        String path1 = GetConfigValue.class.getClassLoader().getResource("/"+name).getPath();
        InputStream path = ClassLoader.getSystemResourceAsStream(name);
        log.info("file path is "+path);
        return path;
    }

    private void initConfig() throws IOException {
        properties.load(getPropertiesPath());
    }

    public String getValue(String name){
        Object o = properties.get(name);
        return o.toString();
    }

    public static GetConfigValue GetInstace(){
        return Instance;
    }
}
