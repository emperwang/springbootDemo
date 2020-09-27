package com.wk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wk.entity.OrderDO;
import com.wk.mapper.OrderMapper;
import com.wk.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.integration.http.DefaultHttpExecutor;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @GlobalTransactional
    public Integer createOrder(Long userId, Long productId, int price) throws Exception {
        // 购买数量, 暂时设置为1
        int amount = 1;
        System.out.println("[createOrder] 当前xid: "+ RootContext.getXID());
        // 扣减库存
        this.reduceStock(productId,amount);
        // 扣减余额
        this.reduceBalance(userId, price*amount);
        // 保存订单
        final OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setProductId(productId);
        orderDO.setPayAmount(price * amount);
        orderMapper.saveOrder(orderDO);
        return orderDO.getId();
    }

    private void reduceStock(Long productId, int amount) throws Exception{
        // 参数拼接
        final JSONObject params = new JSONObject().fluentPut("productId", String.valueOf(productId))
                .fluentPut("amount", String.valueOf(amount));
        // 执行调用
        final HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8082",
                "/product/reduce-stock", params, HttpResponse.class);
        // 解析结果
        final Boolean isSucc = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
        if (!isSucc){
            throw new RuntimeException("扣除库存失败");
        }
    }

    private void reduceBalance(Long userId, int price) throws Exception{
        // 参数拼接
        final JSONObject params = new JSONObject().fluentPut("userId", String.valueOf(userId))
                .fluentPut("price", String.valueOf(price));
        // 执行调用
        final HttpResponse response = DefaultHttpExecutor.getInstance().executePost("http://127.0.0.1:8083",
                "/account/reduce-balance", params, HttpResponse.class);
        // 解析结果
        final Boolean isSucc = Boolean.valueOf(EntityUtils.toString(response.getEntity()));
        if (!isSucc){
            throw new RuntimeException("扣除余额失败");
        }
    }
}
