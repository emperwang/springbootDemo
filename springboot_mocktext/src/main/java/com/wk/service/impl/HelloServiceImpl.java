package com.wk.service.impl;

import com.wk.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl  implements HelloService {


    @Override
    public String getHelloResult() {
        sleep(10);

        return "hello";
    }


    private void sleep(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
