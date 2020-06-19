package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

@Slf4j
@RestController
public class WebAsyncTaskController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "webasyn.do")
    public WebAsyncTask<String> webAsyncTask(){
        String url = "http://192.168.72.1:8888/getUser";
        log.info("current thread : {},into webAsync controller .",Thread.currentThread().getId());
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(3000, new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("into webAsyncTask ,current thread is {}",Thread.currentThread().getId());
                ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
                int statusCodeValue = entity.getStatusCodeValue();
                String body = entity.getBody();
                log.info("statusCodeValue= {},body={}",statusCodeValue,body);
                return body;
            }
        });

        log.info("into controller ...........,thread ={}",Thread.currentThread().getId());
        // 完成的回调方法
        asyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
            log.info("completion thread, current thread is :{}",Thread.currentThread().getId());
            }
        });
        // 超时的回调方法
        asyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("Timeout thread is :{}",Thread.currentThread().getId());
                return null;
            }
        });
        // 错误的回调方法
        asyncTask.onError(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.error("error callBack...............");
                return null;
            }
        });
        log.info("return from  controller .........");
        return asyncTask;
    }
}
