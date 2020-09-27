package com.wk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wk.Mapper.AccountDao;
import com.wk.service.AccountService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    @DS(value = "amount-ds")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reduceBalance(Long userId, Integer price) throws Exception {
        System.out.println("[reduceBalance] 当前xid :" + RootContext.getXID());
        // 检查余额
        checkBalance(userId,price);
        // 扣除余额
        final int i = accountDao.reduceBalance(userId, price);
        // 扣除
        if (i == 0){
            System.out.println("[reduceBalance] 扣除用户: "+userId+" 余额失败.");
        }else{
            System.out.println("[reduceBalance] 扣除用户: "+userId+" 余额成功.");
        }
    }

    private void checkBalance(Long userId, int price) throws Exception {
        System.out.println("checkBalance] 检查用户: "+userId+" 余额.");
        final Integer balance = accountDao.getBalance(userId);
        if (balance < price){
            System.out.println("[checkBalance] 用户: " + userId+" 余额不足,当前余额: "+balance);
            throw new Exception("余额不足.");
        }
    }
}
