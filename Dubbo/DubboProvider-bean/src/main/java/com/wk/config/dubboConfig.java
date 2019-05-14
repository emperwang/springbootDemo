package com.wk.config;

import com.alibaba.dubbo.config.*;
import com.wk.api.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//此配置根据xml文件，有哪些配置，就有对应的beanConfig类
public class dubboConfig {

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("boot-bean-provider");
        return applicationConfig;
    }
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);
        registryConfig.setTimeout(5000);
        registryConfig.setCheck(false);
        return registryConfig;
    }
    @Bean  //参数springboot会自动进行注入
    public ServiceConfig<UserService> serviceConfig(UserService userService){
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);
        serviceConfig.setRef(userService);
        serviceConfig.setRetries(3);

        serviceConfig.setRegistry(registryConfig());
        serviceConfig.setApplication(applicationConfig());
        serviceConfig.setProtocol(protocolConfig());
        serviceConfig.export();
        return serviceConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }
}
