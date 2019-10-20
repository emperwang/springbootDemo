package com.wk.web.controller;

import com.wk.web.service.HystrixSemphareService;
import com.wk.web.service.HystrixThreadService;
import com.wk.web.service.HystrixTimeoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@RestController
public class HystrixController {

    @Autowired
    private HystrixTimeoutService timeoutService;
    @Autowired
    private HystrixSemphareService hystrixSemphareService;
    @Autowired
    private HystrixThreadService hystrixThreadService;
    private int threadConcurrentCount = 100;

    @GetMapping(value = "timeout.do")
    public String hystrixTimeOut(){
        String result = timeoutService.cteateOrder();
        return result;
    }

    @GetMapping(value = "semaphore.do")
    public String hystrixSemaphore(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadConcurrentCount);
        Semaphore semaphore = new Semaphore(threadConcurrentCount);
        for (int i=0;i<threadConcurrentCount;i++){
            threadPool.execute(()->{
                try {
                    String order = hystrixSemphareService.createOrder();
                    log.info("order is :{}", order);
                    semaphore.release();
                } catch (Exception e){
                    log.error("error ,msg is:{}",e.getMessage());
                }
                countDownLatch.countDown();

            });
        }
        return "semaphore";
    }
    // 直接在线程池中执行
    @GetMapping(value = "thread.do")
    public String hystrixThread(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadConcurrentCount);
        Semaphore semaphore = new Semaphore(threadConcurrentCount);
        for (int i=0;i<threadConcurrentCount;i++){
            threadPool.execute(()->{
                try {
                    String order = hystrixThreadService.createOrder();
                    log.info("order is :{}", order);
                    semaphore.release();
                } catch (Exception e){
                    log.error("error ,msg is:{}",e.getMessage());
                }
                countDownLatch.countDown();

            });
        }
        return "thread";
    }
}
