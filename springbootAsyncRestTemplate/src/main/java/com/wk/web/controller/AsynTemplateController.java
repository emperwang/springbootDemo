package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

@RestController
@Slf4j
public class AsynTemplateController {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;
    @GetMapping(value = "asynget.do")
    public String AsyncGet(){
        String url = "http://192.168.72.1:8888/getUser";
        // 设置请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String json = "{\"name\":\"wang\"}";
        HttpEntity<String> httpEntity = new HttpEntity<String>(json,httpHeaders);
        //asyncRestTemplate.execute(url, HttpMethod.GET,);
        ListenableFuture<ResponseEntity<String>> entity = asyncRestTemplate.getForEntity(url, String.class);
        entity.addCallback(new SuccessCallback<ResponseEntity<String>>() {
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                log.info("success thread is :{}",Thread.currentThread().getId());
                int statusCodeValue = result.getStatusCodeValue();
                String body = result.getBody();
                log.info("statusCode :{}, body is:{}",statusCodeValue,body);
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("success thread is :{}",Thread.currentThread().getId());
                log.info("failed , msg is :{}",ex.getMessage());
            }
        });

        // 第二种方法回调
        /*entity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("onFailure thread is :{}",Thread.currentThread().getId());
                log.info("ListenableFutureCallback failed , msg is :{}",ex.getMessage());
            }

            @Override
            public void onSuccess(ResponseEntity<String> result) {
                log.info("onSuccess thread is :{}",Thread.currentThread().getId());
                int statusCodeValue = result.getStatusCodeValue();
                String body = result.getBody();
                log.info("ListenableFutureCallback statusCode :{}, body is:{}",statusCodeValue,body);
            }
        });*/

        log.info("-------------------------------main thread ---------------:{} ",Thread.currentThread().getId());
        return "success";
    }
}
