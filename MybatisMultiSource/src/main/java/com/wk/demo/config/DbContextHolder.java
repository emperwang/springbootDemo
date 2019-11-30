package com.wk.demo.config;

public class DbContextHolder {
    private static final ThreadLocal contextHolder = new ThreadLocal();

    public static void setDbType(DBTypeEnum dbType){
        contextHolder.set(dbType.getValue());
    }

    public static String getDbType(){
        return (String) contextHolder.get();
    }

    public static void removeDbType(){
        contextHolder.remove();
    }
}
