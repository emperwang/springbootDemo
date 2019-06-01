package com.wk.demo.test.redisString;

import com.wk.demo.service.getTemplate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles
@RunWith(SpringRunner.class)
public class BaseTest {
    @Autowired
    private getTemplate template;

    protected RedisTemplate redisTemplate;

    @Before
    public void getRedisOperation(){
        redisTemplate=template.getRedisTemplate();
    }
}
