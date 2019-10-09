package com.wk.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.wk.web.mapper"})
public class MyBatisConfig {
}
