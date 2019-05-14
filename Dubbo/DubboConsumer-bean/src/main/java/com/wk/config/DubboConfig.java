package com.wk.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.wk.api.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("boot-dubbo-bean-consumer");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setCheck(false);
        registryConfig.setTimeout(5000);
        return registryConfig;
    }

    @Bean
    public ReferenceConfig<UserService> referenceConfig(){
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setId("userService");
        referenceConfig.setRetries(3);
        referenceConfig.setCheck(false);
        return referenceConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }
}
