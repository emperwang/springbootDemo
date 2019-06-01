package com.wk.demo.test.redisString;

import com.wk.demo.config.redisConf;
import com.wk.demo.service.getTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@ActiveProfiles   //启用application.properties配置文件
@RunWith(SpringRunner.class)
public class StringTest {

    @Autowired
    private getTemplate getTemplatel;

    private RedisTemplate redisTemplate;

    @Before
    public void init(){
        redisTemplate = getTemplatel.getRedisTemplate();
        System.out.println(redisTemplate);
    }

    @Test
    public void testInsert(){
        redisTemplate.boundValueOps("stringValue2").set("value",6, TimeUnit.MINUTES);
        System.out.println("Success");
    }

    @Test
    public void testGet(){
        String value = (String) redisTemplate.boundValueOps("stringValue2").get();
        System.out.println(value);
    }
    @Test
    public void testUpdate(){
        String value = (String) redisTemplate.boundValueOps("stringValue2").getAndSet("new Value");
        System.out.println(value);
    }
    @Test
    public void testDelete(){
        redisTemplate.delete("stringValue2");
    }
}
