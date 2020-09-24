package com.wk.service;

import com.wk.common.DBConstants;
import com.wk.entity.Order;
import com.wk.entity.User;
import com.wk.mapper.order.OrderMapper;
import com.wk.mapper.user.UserMapper;
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

    private OrderService self(){
        return (OrderService) AopContext.currentProxy();
    }

    /**
     * 为使用 @Transaction 注解,不会开启事务
     * 对于OrderMapper和UserMapper的查询操作,分别使用其接口对应的SqlSessionTemplate,找到对应的数据源 进行操作
     * 这样看来,在未开启事务的情况下,已经能够自由使用多数据源
     */
    public void method01(){
        Order order = orderMapper.selectById(1);
        System.out.println("method01 :" + order);
        User user = userMapper.selectById(1);
        System.out.println("method01 :" + user);
    }

    /**
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type
     * 'org.springframework.transaction.PlatformTransactionManager'
     * available: expected single matching bean but found 2: ordersTransactionManager,userTransactionManager
     * 报错
     * 在@Transaction注解上,如果未设置使用的事务管理器,它会去选择一个事务管理器.但是
     * 我们这里创建了orderTransactionManager和userTransactionManager两个事务管理器,
     * 它就不知道怎么选了
     */
    @Transactional
    public void method02(){
        Order order = orderMapper.selectById(1);
        System.out.println("method01 :" + order);
        User user = userMapper.selectById(1);
        System.out.println("method01 :" + user);
    }

    public void method03(){
        self().method031();
        self().method032();
    }

    @Transactional(transactionManager = DBConstants.TX_MANAGER_USERS)
    public void method031(){
        User user = userMapper.selectById(1);
        System.out.println("method031 : " + user);
    }
    @Transactional(transactionManager = DBConstants.TX_MANAGER_ORDERS)
    public void method032(){
        Order order = orderMapper.selectById(1);
        System.out.println("method031 : "+ order);
    }


    @Transactional(transactionManager = DBConstants.TX_MANAGER_ORDERS)
    public void method05(){
        Order order = orderMapper.selectById(1);
        System.out.println("method05 : " + order);
    }
    @Transactional(transactionManager = DBConstants.TX_MANAGER_USERS,
    propagation = Propagation.REQUIRES_NEW)
    public void method052(){
        User user = userMapper.selectById(1);
        System.out.println("method052: " + user);
    }
}
