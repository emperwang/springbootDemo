package com.wk.quartz.demo.service;

import com.wk.quartz.demo.util.QuartzUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class QuartzTrigger implements ApplicationRunner{

    @Autowired
    private QuartzUtil quartzUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动quarta任务");
        quartzUtil.addOrUpadteJob(TestQuartz.class,
                "testQuartz","testGroup","0/20 * * * * ? *");
    }
}
