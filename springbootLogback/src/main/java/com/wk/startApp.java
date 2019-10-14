package com.wk;

import com.wk.config.logBackConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class startApp {
    public static void main(String[] args) {
        // 第二种方式:把属性设置到System中，这样在logback.xml中就可以获取了,方式如下
        // <springProperty scope="context" name="hostname" source="hostname" />
        String hostName = new logBackConfig().getPropertyValue();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("hostname  = "+hostName);
        System.out.println("--------------------------------------------------------------------");
        System.setProperty("hostname",hostName);
        SpringApplication.run(startApp.class,args);
    }
}
