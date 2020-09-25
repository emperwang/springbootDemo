package com.wk;

import com.wk.Entity.Order;
import com.wk.Entity.User;
import com.wk.repository.order.OrderRepository;
import com.wk.repository.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicSourceJPAStarter.class)
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testUserRep(){
        Iterable<User> all = userRepository.findAll();
        while (all.iterator().hasNext()){
            User next = all.iterator().next();
            System.out.println(next);
        }
    }

    @Test
    public void testOrderRep(){
        Iterator<Order> iterator = orderRepository.findAll().iterator();
        while (iterator.hasNext()){
            Order next = iterator.next();
            System.out.println(next);
        }
    }
}
