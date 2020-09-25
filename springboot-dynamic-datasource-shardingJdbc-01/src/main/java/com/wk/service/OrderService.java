package com.wk.service;

import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.OrderMapper;
import com.wk.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    public OrderService self(){
        return (OrderService) AopContext.currentProxy();
    }

    public void method01(){
        // 查询订单
        Order order = orderMapper.selectById(1);
        System.out.println("method01:" + order);
        // 查询用户
        User user = userMapper.selectById(1);
        System.out.println("method01: " + user);
    }

    @Transactional
    public void method02(){
        // 查询订单
        Order order = orderMapper.selectById(1);
        System.out.println("method02:" + order);
        // 查询用户
        User user = userMapper.selectById(1);
        System.out.println("method02: " + user);
    }

    public void method03(){
        self().method031();
        self().method032();
    }
    @Transactional
    public void method031(){
        Order order = orderMapper.selectById(1);
        System.out.println("method031: " + order);
    }
    @Transactional
    public void method032(){
        User user = userMapper.selectById(1);
        System.out.println("method032 : " + user);
    }
    @Transactional
    public void method04(){
        self().method041();
        self().method042();
    }
    @Transactional
    public void method041(){
        Order order = orderMapper.selectById(1);
        System.out.println("method031: " + order);
    }
    @Transactional
    public void method042(){
        User user = userMapper.selectById(1);
        System.out.println("method032 : " + user);
    }

    @Transactional
    public void method05(){
        Order order = orderMapper.selectById(1);
        System.out.println("method05:" + order);
        self().method052();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method052(){
        User user = userMapper.selectById(1);
        System.out.println("method052:" + user);
    }
}
