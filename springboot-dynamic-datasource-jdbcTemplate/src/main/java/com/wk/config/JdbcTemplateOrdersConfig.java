package com.wk.config;

import com.wk.common.DBConstances;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateOrdersConfig {

    /**
     * 创建数据源
     */
    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orders")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建 template
     */
    @Bean(name = DBConstances.JDBC_TEMPLATE_ORDERS)
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate((this.dataSource()));
    }

    /**
     * 创建order的事务管理器
     */
    @Bean(name = DBConstances.TX_MANAGER_ORDERS)
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }

}
