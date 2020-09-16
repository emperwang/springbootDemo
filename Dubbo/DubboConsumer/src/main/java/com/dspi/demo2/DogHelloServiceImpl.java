package com.dspi.demo2;

import com.alibaba.dubbo.common.URL;

public class DogHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello dog.");
    }

    @Override
    public void sayHello(URL url) {
        System.out.println("dog hello  " + url);
    }
}
