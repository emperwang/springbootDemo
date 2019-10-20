package com.wk.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HystrixThreadService {

    // 线程池隔离策略
    @HystrixCommand(
            commandKey = "createOrder",
            commandProperties = {
                    @HystrixProperty(name = "exection.isolation.strategy",value = "THREAD")
            },
            threadPoolKey = "createOrderThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "3"),
                    @HystrixProperty(name = "maxQueueSize",value = "5"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold",value = "7")
            },
            fallbackMethod = "createOrderFallbackMethodThread"
    )
    public String createOrder(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "order success";
    }

    public String createOrderFallbackMethodThread(){
        log.info("线程池降级策略执行");
        return "hystrix thread full";
    }
}
