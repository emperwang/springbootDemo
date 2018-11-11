package com.wk.actuator.demo.healthdemo;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * 自定义健康端点方式2，这个方式功能更加强大一点
 * DataSourceHealthIndicator/RedisHealthIndicator都是这种写法
 */
@Component
public class MyHealthTwo extends AbstractHealthIndicator{

    private static final String VERSION="v2.0.0";
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = check();
        if(code != 0){
            builder.down()
                    .withDetail("msg","something wrong")
                    .withDetail("code",code)
                    .withDetail("version",VERSION)
                    .build();
        }
        builder.up().withDetail("code",code)
                .withDetail("msg","ok啦")
                .withDetail("version",VERSION)
                .build();
    }

    /**
     * 返回0  表示正常
     * 这里是具体做一些检查的操作
     * @return
     */
    private int check(){
        return 0;
    }
}
