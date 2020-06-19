package com.wk.demo.config;

public enum DBTypeEnum {

    db1("db1"), db2("db2"), db3("db3");

    private String value;

    DBTypeEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
