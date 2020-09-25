package com.wk.service;

import com.wk.common.DBConstances;
import com.wk.entity.OrderDao;
import com.wk.entity.UserDao;
import com.wk.repository.OrderRepository;
import com.wk.repository.UserRepository;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    private OrderService self(){
        return (OrderService) AopContext.currentProxy();
    }

    public void method01(){
        // 查询订单
        OrderDao orderDao = orderRepository.selectById(1);
        System.out.println(orderDao);
        // 查询用户
        UserDao userDao = userRepository.selectById(1);
        System.out.println(userDao);
    }

    @Transactional  // 报错, 找不到事务管理器
    public void method02(){
        // 查询订单
        OrderDao orderDao = orderRepository.selectById(1);
        System.out.println("method02:" + orderDao);
        // 查询用户
        UserDao userDao = userRepository.selectById(1);
        System.out.println("method02 :"+userDao);
    }

    public void method03(){

    }

    @Transactional(transactionManager = DBConstances.TX_MANAGER_ORDERS)
    public void method031(){
        OrderDao orderDao = orderRepository.selectById(1);
        System.out.println(orderDao);
    }
    @Transactional(transactionManager = DBConstances.TX_MANAGER_USERS)
    public void method032(){
        UserDao userDao = userRepository.selectById(1);
        System.out.println(userDao);
    }

    @Transactional(transactionManager = DBConstances.TX_MANAGER_USERS)
    public void method05(){
        UserDao userDao = userRepository.selectById(1);
        System.out.println("method05: " + userDao);
    }
    @Transactional(transactionManager = DBConstances.TX_MANAGER_ORDERS,
    propagation = Propagation.REQUIRES_NEW)
    public void method051(){
        OrderDao orderDao = orderRepository.selectById(1);
        System.out.println("method051: " + orderDao);
    }
}
