package com.wk.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncServiceImpl {

    @Async
    public void doTaskA() throws InterruptedException {
        System.out.println("doTaskA thread name --> " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long end = System.currentTimeMillis();
        System.out.println(" doTask A 耗时: " + (end-start));
    }
    // 1. 在这里指定 要使用的线程池 bean name
    //@Async("executor")
    // 2. 使用容器中TaskExecutor 类型的 线程池
    // 3. 使用容器中名字为 taskExecutor 的线程池
    @Async
    public void doTaskB() throws InterruptedException {
        System.out.println("doTaskB thread name --> " + Thread.currentThread().getName());
        long start = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(4);
        long end = System.currentTimeMillis();
        System.out.println(" doTask A 耗时: " + (end-start));
    }

}
