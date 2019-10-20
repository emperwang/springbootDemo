package com.wk.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HystrixSemphareService {
    // 信号量隔离策略
    @HystrixCommand(
            commandKey = "createOrder",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "6")
            },
            fallbackMethod = "createOrderFallbackMethodSemaphore"
    )
    public String createOrder(){
        return "order success";
    }

    public String createOrderFallbackMethodSemaphore(){
        log.info("semaphore 降级策略");
        return "semaphore";
    }
}
