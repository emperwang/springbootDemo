package com.wk.service;

public interface AccountService {
    void reduceBalance(Long userId, Integer price) throws Exception;
}
