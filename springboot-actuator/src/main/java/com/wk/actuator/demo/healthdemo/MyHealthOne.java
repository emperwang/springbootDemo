package com.wk.actuator.demo.healthdemo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 自定义健康端点方式一
 */
@Component
public class MyHealthOne implements HealthIndicator{

    private static final String VERSION = "v1.0.0";

    @Override
    public Health health() {
        int code = check();
        if (code != 0){
            return  Health.down().withDetail("code",code)
                    .withDetail("version",VERSION)
                    .withDetail("error","something wrong").build();
        }
        return Health.up().withDetail("code",code)
                .withDetail("version",VERSION)
                .withDetail("startup","yes")
                .withDetail("msg","ok啦")
                .build();
    }

    /**
     * 返回0 表示检测结果成功
     * @return
     */
    private int check(){
        return 0;
    }
}
