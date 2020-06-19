package com.wk.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HystrixThreadService {
    @Autowired
    private RestTemplate restTemplate;
    // 线程池隔离策略
    @HystrixCommand(
            commandKey = "createOrder",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy",value = "THREAD")
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
        String order = restTemplate.getForObject("http://localhost:9090/getOrder.do", String.class);
        return "order success,thread order :"+order;
    }

    public String createOrderFallbackMethodThread(){
        log.info("线程池降级策略执行");
        return "hystrix thread full";
    }
}
