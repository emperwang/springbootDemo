package com.wk.controller;

import com.wk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Integer createOrder(@RequestParam("userId")Long userId,
                               @RequestParam("productId")Long productId,
                               @RequestParam("price")int price) throws Exception {
        System.out.println("[createOrder] 收到下单请求,用户: "+userId+", 商品: "+productId+", 价格:"+price);
        return orderService.createOrder(userId,productId,price);
    }
}
