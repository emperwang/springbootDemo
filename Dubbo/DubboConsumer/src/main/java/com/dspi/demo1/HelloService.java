package com.dspi.demo1;

import com.alibaba.dubbo.common.extension.SPI;

//@SPI  // 获取到所有的实现类
@SPI("dog") // 此操作会初始化dog名字对应的实现类
public interface HelloService {
    void sayHello();
}
