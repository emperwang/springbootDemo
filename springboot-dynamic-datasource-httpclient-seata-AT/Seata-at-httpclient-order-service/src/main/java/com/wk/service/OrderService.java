package com.wk.service;

public interface OrderService {
    /**
     * 创建订单
     * @param userId
     * @param productId
     * @param price
     * @return
     * @throws Exception
     */
    Integer createOrder(Long userId, Long productId, int price) throws Exception;
}
