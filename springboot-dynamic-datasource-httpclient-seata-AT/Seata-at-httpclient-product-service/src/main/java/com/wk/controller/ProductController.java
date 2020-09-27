package com.wk.controller;

import com.wk.entity.ProductReduceStockDTO;
import com.wk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/reduce-stock")
    public Boolean reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO){
        System.out.println("[reduceStock] 收到减少库存请求,商品: "+productReduceStockDTO.getProductId()+", 数量: "+
                productReduceStockDTO.getAmount());
        try {
            productService.reduceStock(productReduceStockDTO.getProductId(), productReduceStockDTO.getAmount());
            return true;
        }catch (Exception e){
            // 扣除库存失败
            return false;
        }
    }
}
