package com.wk.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Sparks
 * @Date: 2021/5/9 15:11
 * @Description
 */
@Component
public class FixRateTask {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void FixRateTask(){
        System.out.println("begin time： " + format.format(new Date()));
        System.out.println("fix rate task begin...");
        System.out.println();
    }

    @Schedules(value = {
            @Scheduled(cron = "*/5 * * * * *")
    })
    public void cronTask(){
        System.out.println();
        System.out.println("cron task start: " + format.format(new Date()));
        System.out.println("cron task: 5 * * * * *");
        System.out.println();
    }

    public void delayTask(){

    }
}
