package com.wk.web.service;

import com.wk.config.HystrixConfig;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        HystrixConfig.class,
        HystrixThreadService.class,
        HystrixSemphareService.class
})
public class HystrixTest {

    @Autowired
    private HystrixThreadService hystrixThreadService;

    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 10,threads = 30)
    public void test1(){
        System.out.println("test-------------------------------");
        String order = hystrixThreadService.createOrder();
        System.out.println("order result is :"+order);
    }
}
