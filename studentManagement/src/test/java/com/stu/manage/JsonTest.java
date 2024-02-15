package com.stu.manage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Sparks
 * @Date: 2024/2/7 21:18
 * @Description
 */
public class JsonTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJson1() throws JsonProcessingException {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("username", "zhangsan");
        map.put("政策与形式", "70");
        map.put("计算机", "80");
        map.put("高等数学", "90");
        String value = objectMapper.writeValueAsString(map);
        System.out.println(value);
    }

    @Test
    public void testJson2() throws JsonProcessingException {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("username", "zhangsan");
        map.put("政策与形式", "70");
        map.put("计算机", "80");
        map.put("高等数学", "90");

        List<Map> result = new ArrayList<>();
        result.add(map);

        map = new ConcurrentHashMap<>();
        map.put("username", "wangwu");
        map.put("政策与形式", "70");
        map.put("计算机", "80");
        map.put("高等数学", "90");
        result.add(map);

        System.out.println(objectMapper.writeValueAsString(result));
    }
}
