package com.wk.service;

import com.wk.repository.order.OrderRepository;
import com.wk.repository.user.UserRepository;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private OrderService self(){
        return (OrderService) AopContext.currentProxy();
    }

    public void method01(){

    }

    public void method02(){

    }

    public void method03(){

    }

    public void method04(){

    }

    public void method05(){

    }
}
