package com.wk;

import com.wk.entity.Order;
import com.wk.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicSource02Starter.class)
public class SpringTest {

    @Autowired
    private OrderMapper orderMapper;



    @Test
    public void testSelect(){
        for (int i =0 ; i< 10; i++) {
            Order order = orderMapper.selectById(i);
            System.out.println("select: " + order);
        }
    }

    @Test
    public void testInsert(){
        int insert = orderMapper.insert(new Order(5, 5));
        System.out.println("insert: " + insert);
    }

}
