package com.wk.web.controller;

import com.wk.config.DeferredResultConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@RestController
public class DeferredController {

    @Autowired
    private DeferredResultConfig resultConfig;

    @GetMapping(value = "deferred.do")
    public DeferredResult<String> executeSlowTask(){
        log.info("into DeferredResult executeSlowTask ,thread = {}",Thread.currentThread().getId());

        DeferredResult<String> deferredResult = new DeferredResult<>();
        // 异步执行
        resultConfig.execute(deferredResult);
        // 绑定回调方法
        deferredResult.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.error("deferredResult Timeout ...................");
            }
        });

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("deferredResult onCompletion...........");
            }
        });
        log.info("current thread is :{}, result is :{}",Thread.currentThread().getId());
        return deferredResult;
    }
}
