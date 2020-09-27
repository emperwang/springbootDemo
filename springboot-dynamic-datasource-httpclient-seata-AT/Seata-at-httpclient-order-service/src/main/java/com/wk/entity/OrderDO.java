package com.wk.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class OrderDO {
    // 订单编号
    private Integer id;
    // 用户编号
    private Long userId;
    // 产品编号
    private Long productId;
    // 支付金额
    private Integer payAmount;
}
