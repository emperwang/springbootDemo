package com.dspi.demo2;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class DSPI2Starter {
    public static void main(String[] args) {
        /**
         * 这里注意? 后面:
         * hello.service=HelloService= 后面就是在配置文件中的key
         */
        //URL url = URL.valueOf("test://localhost/hello?hello.service=human");
        URL url = URL.valueOf("test://localhost/hello");
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        HelloService adaptive = extensionLoader.getAdaptiveExtension();
        adaptive.sayHello(url);
    }
}
