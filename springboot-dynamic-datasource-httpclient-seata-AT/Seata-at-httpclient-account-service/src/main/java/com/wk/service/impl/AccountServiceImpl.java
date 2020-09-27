package com.wk.service.impl;

import com.wk.mapper.AccountDao;
import com.wk.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void reduceBalance(Long userId, Integer price) throws Exception {
        log.info("[reduceBalance] 当前xid: {}", RootContext.getXID());
        // 检查余额
        this.checkBalance(userId,price);
        // 扣除余额
        final int updateCount = accountDao.reduceBalance(price);
        // 是否扣除成功
        if (updateCount == 0){
            log.info("[reduceBalance] 扣除用户:{} 余额失败.",userId);
            throw new Exception("用户余额不足");
        }else{
            log.info("[reduceBalance] 扣除用户:{} 余额成功.",userId);
        }
    }

    private void checkBalance(Long userId, Integer requirePrice) throws Exception {
        log.info("[checkBalance] 检查用户{},余额", userId);
        final Integer balance = accountDao.getBalance(userId);
        if (balance < requirePrice){
            log.warn("[checkBalance] 用户:{} 余额不足,当前余额:{}",userId, balance);
            throw new Exception("用户余额不足.");
        }
    }
}
