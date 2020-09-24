package com.wk;

import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.order.OrderMapper;
import com.wk.mapper.user.UserMapper;
import com.wk.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = mybatisMultiSourceStarter.class)
public class SpringTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

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
    public void testMethod05(){
        orderService.method05();
    }

    @Test
    public void testUserMapper(){
        User user = userMapper.selectById(1);
        System.out.println(user);
        Order order = orderMapper.selectById(1);
        System.out.println(order);
    }
}
