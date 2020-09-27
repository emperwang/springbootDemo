package com.wk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.Mapper.OrderDao;
import com.wk.entity.OrderDO;
import com.wk.service.AccountService;
import com.wk.service.OrderService;
import com.wk.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;


    @Override
    @DS(value = "order-ds")
    @GlobalTransactional  // 声明全局事务
    public Integer createOrder(Long userId, Long productId, Integer price) throws Exception {
        int amount = 1;
        System.out.println("[createOrder] 当前XID: " + RootContext.getXID());
        // 扣减库存
        productService.reduceStock(productId,amount);
        // 扣减余额
        accountService.reduceBalance(userId,price);
        // 保存订单
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setProductId(productId);
        orderDO.setPayAmount(price * amount);
        orderDao.saveOrder(orderDO);
        return orderDO.getId();
    }
}
