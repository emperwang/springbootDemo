package com.wk.quartz.demo.service;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 当任务运行时间超时时，默认会启动另一个线程池中任务来执行此任务
 * @DisallowConcurrentExecution 加上此注解后，不允许并行执行
 */
@DisallowConcurrentExecution
public class TestSimpleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date fireTime = context.getTrigger().getNextFireTime();
        System.out.println("");
        System.out.println("next firetime is :"+fireTime);
        System.out.println("this is simple job");
        System.out.println("");
        try {
            System.out.println(Thread.currentThread().getName());
            System.out.println("into sleep");
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
