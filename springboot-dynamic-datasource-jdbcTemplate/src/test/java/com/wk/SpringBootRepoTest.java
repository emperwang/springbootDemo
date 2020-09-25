package com.wk;

import com.wk.entity.OrderDao;
import com.wk.entity.UserDao;
import com.wk.repository.OrderRepository;
import com.wk.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicTemplateStarter.class)
public class SpringBootRepoTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUser(){
        UserDao userDao = userRepository.selectById(1);
        System.out.println(userDao);
    }
    @Test
    public void testOrder(){
        OrderDao orderDao = orderRepository.selectById(1);
        System.out.println(orderDao);
    }
}
