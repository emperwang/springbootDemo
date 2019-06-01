package com.wk.demo.test.redisString;

import org.junit.Test;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashTest extends BaseTest {

    @Test
    public void testInsert(){
        redisTemplate.boundHashOps("hashValue").put("name","wk1");
        redisTemplate.boundHashOps("hashValue").put("name1","wk2");
        redisTemplate.boundHashOps("hashValue").put("name2","wk3");
        redisTemplate.boundHashOps("hashValue").put("name3","wk4");
        redisTemplate.boundHashOps("hashValue").put("name4","wk5");
        redisTemplate.boundHashOps("hashValue").put("name5","wk6");
        redisTemplate.boundHashOps("hashValue").put("name6","wk7");

    }
    @Test
    public void getValue(){
        Object o = redisTemplate.boundHashOps("hashValue").get("name");
        System.out.println(o.toString());
    }
    @Test
    public void getMapValue(){
        Map hashValue = redisTemplate.boundHashOps("hashValue").entries();
        Set<Map.Entry> set = hashValue.entrySet();
        for (Map.Entry entry : set) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            System.out.println("key="+key.toString()+",value="+value.toString());
        }
    }
    @Test
    public void getKeys(){
        Set keys = redisTemplate.boundHashOps("hashValue").keys();
        for (Object key : keys) {
            System.out.println(key.toString());
        }
    }
    @Test
    public void containKey(){
        Boolean name = redisTemplate.boundHashOps("hashValue").hasKey("name");
        System.out.println(name);
    }

    @Test
    public void getFieldsNum(){
        Long size = redisTemplate.boundHashOps("hashValue").size();
        System.out.println("fields is :"+size);
    }

    @Test
    public void multiGet(){
        ArrayList<String> list = new ArrayList<>();
        list.add("name");
        List hashValue = redisTemplate.boundHashOps("hashValue").multiGet(list);
        for (Object o : hashValue) {
            System.out.println(o.toString());
        }
    }
    @Test
    public void scanTest(){
        ScanOptions.ScanOptionsBuilder scanOptionsBuilder = ScanOptions.scanOptions();
        scanOptionsBuilder.match("name*");
        scanOptionsBuilder.count(100);
        ScanOptions scanOptions = scanOptionsBuilder.build();
        Cursor hashValue = redisTemplate.boundHashOps("hashValue").scan(scanOptions);
        while(hashValue.hasNext()){
            System.out.println("cursorId is :"+ hashValue.getCursorId());
            System.out.println("position is :"+ hashValue.getPosition());
            Object next = hashValue.next();
            System.out.println(next.toString());
        }
    }

    @Test
    public void testPutIfNotPresent(){
        redisTemplate.boundHashOps("hashValue").putIfAbsent("name","wangliu");
    }

    @Test
    public void getValues(){
        List values = redisTemplate.boundHashOps("hashValue").values();
        for (Object value : values) {
            System.out.println(value.toString());
        }
    }
}
