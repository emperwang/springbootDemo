package com.wk.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
@Slf4j
@RestController
public class CallableController {

    @Autowired
    private RestTemplate restTemplate;

    /** 异步调用restful接口
     * 此是实现异步调用的方式一种
     * 当controller返回值是Callable的时候,springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServelt还有所有的spring拦截器都退出主线程. 然后把response保持打开的状态
     * 当Callable执行结束之后,springmvc就会重新启动分配一个request请求,然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果. 然后返回视图
     * @return
     */
    @GetMapping(value = "callable.do")
    public Callable<String> callable(){
        String url = "http://192.168.72.1:8888/getUser";
        log.info("current thread : {}",Thread.currentThread().getId());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("call thread. current thread is :{}, into  call",Thread.currentThread().getId());
                ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
                int statusCodeValue = entity.getStatusCodeValue();
                String body = entity.getBody();
                log.info("statusCodeValue= {},body={}",statusCodeValue,body);
                return body;
            }
        };
        log.info("main Thread:{}, return from controller..",Thread.currentThread().getId());
        return callable;
    }

}
