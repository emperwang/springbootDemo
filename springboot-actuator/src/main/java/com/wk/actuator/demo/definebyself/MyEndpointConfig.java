package com.wk.actuator.demo.definebyself;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyEndpointConfig {

    @Bean
    @ConditionalOnEnabledEndpoint
    //@ConditionalOnMissingBean
    public MyEndpointOne getMyEndpoint(){
        return  new MyEndpointOne();
    }
}
