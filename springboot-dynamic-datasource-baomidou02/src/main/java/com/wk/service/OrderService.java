package com.wk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.common.DBConstants;
import com.wk.entity.Order;
import com.wk.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     *  此处显示的指定了事务内的源是master, 故下面的读写都在一个 master主库中进行
     */
    @Transactional
    @DS(DBConstants.DATASOURCE_MASTER)
    public void add(Order order){
        Order order1 = orderMapper.selectById(order.getId());

        orderMapper.insert(order);
    }

    /**
     * 此处的没有指定数据源, 所以此处的查询就是用的是 mapper中指定的slave数据源
     */
    public void findById(int id){
        Order order = orderMapper.selectById(1);
    }
}
