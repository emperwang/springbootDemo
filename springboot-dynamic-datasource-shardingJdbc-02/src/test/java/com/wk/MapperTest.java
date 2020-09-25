package com.wk;

import com.wk.entity.Order;
import com.wk.mapper.OrderMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingMultiSource02Starter.class)
public class MapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testOrder(){
        for (int i=0; i<10; i++) {
            Order order = orderMapper.selectById(i);
            System.out.println(order);
        }
    }

    // 强制访问主库
    @Test
    public void testSelectById02(){
        try(HintManager instance = HintManager.getInstance()){
            // 强制访问主库
            instance.setMasterRouteOnly();

            final Order order = orderMapper.selectById(1);
            System.out.println(order);
        }
    }

    @Test
    public void testInsert(){
        final Order order = new Order();
        order.setId(10);
        order.setUserId(7);
        final int i = orderMapper.saveOrder(order);
        System.out.println("insert : " + i);
    }
}
