package com.wk.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HystrixSemphareService {
    @Autowired
    private RestTemplate restTemplate;
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
        String order = restTemplate.getForObject("http://localhost:9090/getOrder.do", String.class);
        return "order success,semaphore order :"+order;
    }

    public String createOrderFallbackMethodSemaphore(){
        log.info("semaphore 降级策略");
        return "semaphore";
    }
}
