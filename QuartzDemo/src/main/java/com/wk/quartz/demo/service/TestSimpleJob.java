package com.wk.quartz.demo.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class TestSimpleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date fireTime = context.getTrigger().getNextFireTime();
        System.out.println("");
        System.out.println("next firetime is :"+fireTime);
        System.out.println("this is simple job");
        System.out.println("");
    }
}
