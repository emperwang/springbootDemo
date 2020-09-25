package com.wk;

import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.OrderMapper;
import com.wk.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingMultiSourceStarter.class)
public class MapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser(){
        Order order = orderMapper.selectById(1);
        System.out.println(order);

        User user = userMapper.selectById(1);
        System.out.println(user);
    }
}