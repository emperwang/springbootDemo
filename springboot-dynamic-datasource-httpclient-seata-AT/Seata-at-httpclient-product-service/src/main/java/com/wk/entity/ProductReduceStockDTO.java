package com.wk.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class ProductReduceStockDTO {
    private Long productId;
    private Integer amount;
}
