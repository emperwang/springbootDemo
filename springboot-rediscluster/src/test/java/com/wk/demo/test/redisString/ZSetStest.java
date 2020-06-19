package com.wk.demo.test.redisString;

import org.junit.Test;

import java.util.Set;

public class ZSetStest extends BaseTest {

    @Test
    public void testInset(){
        redisTemplate.boundZSetOps("zsetValue").add("key1",1);
        redisTemplate.boundZSetOps("zsetValue").add("key2",2);
        redisTemplate.boundZSetOps("zsetValue").add("key3",3);
        redisTemplate.boundZSetOps("zsetValue").add("key4",4);
        redisTemplate.boundZSetOps("zsetValue").add("key5",5);
        redisTemplate.boundZSetOps("zsetValue").add("key6",6);
        redisTemplate.boundZSetOps("zsetValue").add("key7",7);

        redisTemplate.boundZSetOps("zsetValue1").add("key1",1);
        redisTemplate.boundZSetOps("zsetValue1").add("key2",2);
        redisTemplate.boundZSetOps("zsetValue1").add("key3",3);
        redisTemplate.boundZSetOps("zsetValue1").add("key4",4);
        redisTemplate.boundZSetOps("zsetValue1").add("key8",5);
        redisTemplate.boundZSetOps("zsetValue1").add("key9",6);
        redisTemplate.boundZSetOps("zsetValue1").add("key10",7);



    }
    @Test
    public void unionSet(){
        redisTemplate.boundZSetOps("zsetValue").unionAndStore("zsetValue1","zsetValue11");
        Set zsetValue = redisTemplate.boundZSetOps("zsetValue11").range(0, 20);
        for (Object o : zsetValue) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void innerSet(){
        redisTemplate.boundZSetOps("zsetValue").intersectAndStore("zsetValue1","zsetValue12");
        Set zsetValue = redisTemplate.boundZSetOps("zsetValue12").range(0, 20);
        for (Object o : zsetValue) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void getSize(){
        Long value = redisTemplate.boundZSetOps("zsetValue").zCard();
        Long size = redisTemplate.boundZSetOps("zsetValue").size();
        System.out.println("card is :"+value+", size is:"+size);
    }

    @Test
    public void reverseValue(){
        Set zsetValue = redisTemplate.boundZSetOps("zsetValue").reverseRange(2, 5);
        for (Object o : zsetValue) {
            System.out.println(o.toString());
        }
    }

    @Test
    public void getCount(){
        Long zsetValue = redisTemplate.boundZSetOps("zsetValue").count(1, 7);
        System.out.println("count is:"+zsetValue);
    }
    @Test
    public void getValueByRang(){
        Set zsetValue = redisTemplate.boundZSetOps("zsetValue").range(1, 7);
        for (Object o : zsetValue) {
            System.out.println(o.toString());
        }
    }
    @Test
    public void getRangeByScore(){
        Set zsetValue = redisTemplate.boundZSetOps("zsetValue").rangeByScore(2, 5);
        for (Object o : zsetValue) {
            System.out.println(o.toString());
        }
    }


}
