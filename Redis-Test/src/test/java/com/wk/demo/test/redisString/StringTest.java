package com.wk.demo.test.redisString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ActiveProfiles
@RunWith(SpringRunner.class)
public class StringTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testInsert(){
        System.out.println(redisTemplate);
        redisTemplate.boundValueOps("stringValue").set("嘻哈",6, TimeUnit.MINUTES);
        System.out.println("Success");
    }

    @Test
    public void testGet(){
        String value = (String) redisTemplate.boundValueOps("stringValue").get();
        System.out.println(value);
    }
}
