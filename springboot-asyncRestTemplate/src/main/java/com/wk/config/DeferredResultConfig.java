package com.wk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@Component
public class DeferredResultConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public void execute(DeferredResult<String> deferredResult){
        log.info("DeferredResultConfig execute, thread = {}",Thread.currentThread().getId());
        String url = "http://192.168.72.1:8888/getUser";

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        int statusCodeValue = entity.getStatusCodeValue();
        String body = entity.getBody();
        log.info("get result is :statusCodeValue={},body={} ",statusCodeValue,body);
        deferredResult.setResult(body);
    }

}
