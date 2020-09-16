package com.dspi.demo1;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

public class DSpiStarter {
    public static void main(String[] args) {
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        // 获取所有支持的 实现类
        Set<String> extensions = extensionLoader.getSupportedExtensions();
        extensions.forEach(s -> {
            System.out.println("s = " + s);
            extensionLoader.getExtension(s).sayHello();
        });

        System.out.println("===============================");
        // 获取默认的实现类，即 @SPI("dog") 注解上指定的key对应的实现类
        HelloService defaultExtension = extensionLoader.getDefaultExtension();
        defaultExtension.sayHello();
    }
}
