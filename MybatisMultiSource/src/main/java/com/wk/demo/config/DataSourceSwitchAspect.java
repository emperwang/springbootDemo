package com.wk.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = -1)
@Component
@Slf4j
@Aspect
public class DataSourceSwitchAspect {

    @Pointcut("execution(* com.wk.demo.mapper.mysql..*.*(..))")
    private void db1Aspect(){

    }
    @Pointcut("execution(* com.wk.demo.mapper.postgresql..*.*(..))")
    private void db2Aspect(){

    }

    @Before("db1Aspect()")
    public void db1(){
        log.info("check db1 ..");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }
    @Before("db2Aspect()")
    public void db2(){
        log.info("check db2..");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }

    @After("db1Aspect()")
    public void db1After(){
        log.info("db1 after");
        DbContextHolder.removeDbType();
    }

    @After("db2Aspect()")
    public void db2After(){
        log.info("db2 after");
        DbContextHolder.removeDbType();
    }
}
