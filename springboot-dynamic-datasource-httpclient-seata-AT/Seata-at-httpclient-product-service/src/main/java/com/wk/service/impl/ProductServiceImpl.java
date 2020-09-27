package com.wk.service.impl;

import com.wk.mapper.ProductDao;
import com.wk.service.ProductService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public void reduceStock(Long produceId, Integer amount) throws Exception {
        System.out.println("[reduceStock]当前xid: "+ RootContext.getXID());
        // 检查库存
        this.checkStock(produceId,amount);
        // 扣减库存
        System.out.println("[reduceStock]开始扣减库存 " + produceId+" 库存.");
        final int updateCount = productDao.reduceStock(produceId, amount);
        // 是否扣除成功
        if (updateCount == 0){
            System.out.println("[reduceStock] 扣除 "+produceId+" 库存失败.");
            throw new Exception("库存不足.");
        }else{
            System.out.println("[reduceStock] 扣除 "+produceId+" 库存成功.");
        }
    }

    private void checkStock(Long productId, Integer requireAmount) throws Exception {
        System.out.println("[checkStock] 检查: "+productId+" 库存");
        final Integer stock = productDao.getStock(productId);
        if (stock < requireAmount){
            System.out.println("[checkStock] "+ productId+"  库存不足");
            throw new Exception("库存不足.");
        }
    }
}
