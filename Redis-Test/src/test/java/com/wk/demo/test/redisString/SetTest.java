package com.wk.demo.test.redisString;

import org.junit.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SetTest extends BaseTest {

    @Test
    public void setInset(){
        redisTemplate.boundSetOps("setValue").add("key1",6, TimeUnit.MINUTES);
        redisTemplate.boundSetOps("setValue").add("key2",6, TimeUnit.MINUTES);
        redisTemplate.boundSetOps("setValue").add("key3",6, TimeUnit.MINUTES);
    }
    @Test
    public void setInset2(){
        redisTemplate.boundSetOps("setValue2").add("key1","keys");
    }
    @Test
    public void getSetValue(){
        Set members = redisTemplate.boundSetOps("setValue").members();
        for (Object member : members) {
            System.out.println(member.toString());
        }
    }

    @Test
    public void testInnerSet(){
        Set intersect = redisTemplate.boundSetOps("setValue").intersect("setValue2");
        for (Object o : intersect) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void testDiff(){
        Set diff = redisTemplate.boundSetOps("setValue").diff("setValue2");
        for (Object o : diff) {
            System.out.println(o.toString());
        }
    }
    @Test
    public void contains(){
        Boolean member = redisTemplate.boundSetOps("setValue").isMember("key1");
        System.out.println(member);
    }

    @Test
    public void deleteKey(){
        redisTemplate.delete("setValue");
    }
}
