package com.wk.web.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HystrixTimeoutService {

    @Autowired
    private RestTemplate restTemplate;

    // 超时降级
    @HystrixCommand(
            commandKey = "createOrder",
            commandProperties = {
                @HystrixProperty(name = "execution.timeout.enable",value = "true"),
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000") //ms
            },
            fallbackMethod = "createOrderFallback"
    )
    public String cteateOrder(){
        return "order success";
    }

    // 超时降级方法
    public String createOrderFallback(){
        log.info("超时降级");
        return "hystrix timeout";
    }


}
