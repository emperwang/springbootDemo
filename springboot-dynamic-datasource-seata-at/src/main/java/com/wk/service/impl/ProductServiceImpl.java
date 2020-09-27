package com.wk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.Mapper.ProductDao;
import com.wk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    @DS(value = "storage-ds")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reduceStock(Long productId, Integer amount) throws Exception {
        checkStock(productId,amount);
        System.out.println("[reductStock] 开始扣减库存 : " + productId);
        // 扣减库存
        int i = productDao.reduceStock(productId, amount);
        if (i == 0){
            System.out.println("[reduceStock] 扣除 "+productId+": 库存失败");
        }else {
            System.out.println("[reduceStock] 扣除 "+productId+": 库存成功");
        }
    }

    private void checkStock(Long productId, Integer requireAmount) throws Exception{
        System.out.println("[checkStock] 库存: "+ productId);
        final Integer stock = productDao.getStock(productId);
        if (stock < requireAmount){
            System.out.println("[checkStock] "+productId + ": 库存不足, 当前库存: " + stock);
            throw new Exception("库存不足.");
        }
    }
}



















