package com.wk.controller;

import com.wk.entity.AccountDTO;
import com.wk.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/reduce-balance")
    public Boolean reduceBalance(@RequestBody AccountDTO accountDTO){
        log.info("[reduceBalance] 收到减少余额请求,用户:{}, 金额:{}",accountDTO.getUserId(),
                accountDTO.getPrice());
        try{
            accountService.reduceBalance(accountDTO.getUserId(),accountDTO.getPrice());
            return true;
        }catch (Exception e){
            // 扣除余额失败
            return false;
        }
    }
}
