package com.dspi.demo2;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

//@SPI
@SPI("dog")
public interface HelloService {
    void sayHello();
    @Adaptive
    void sayHello(URL url);
}
