package com.dspi.demo2;

import com.alibaba.dubbo.common.URL;

public class HumanHelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("human say hello.");
    }

    @Override
    public void sayHello(URL url) {
        System.out.println("hello human  " + url);
    }
}
