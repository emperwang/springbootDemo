package com.wk;

import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.OrderMapper;
import com.wk.mapper.UserMapper;
import com.wk.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicSourceStarter.class)
public class BootTest {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderService orderService;

    @Test
    public void testMethod01(){
        orderService.method01();
    }

    @Test
    public void testMethod02(){
        orderService.method02();
    }

    @Test
    public void testMethod03(){
        orderService.method03();
    }

    @Test
    public void testMethod033(){
        orderService.method033();
    }

    @Test
    public void testMethod04(){
        orderService.method04();
    }

    @Test
    public void testMethod05(){
        orderService.method05();
    }

    @Test
    public void testSelectById(){
        Order order = orderMapper.selectById(1);
        System.out.println(order.toString());
    }

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(1);
        System.out.println(user.toString());
    }
}
