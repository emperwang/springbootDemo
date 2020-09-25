package com.wk.config;

import com.wk.common.DBConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = DBConstants.ENTITY_MANAGER_FACTORY_ORDERS,
        transactionManagerRef = DBConstants.TX_MANAGER_ORDERS,
        basePackages = {"com.wk.repository.order"})
public class JpaOrderConfig {

    @Resource(name = "hibernateVendorProperties")
    private Map<String,Object> hibernateVendorProperties;

    /**
     * 创建Orders 数据源
     */
    @Bean(name = "orderDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orders")
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建 LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = DBConstants.ENTITY_MANAGER_FACTORY_ORDERS)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder){

        return builder
                .dataSource(this.dataSource())  // 数据源设置
                .properties(hibernateVendorProperties) // 获取并注入 hibernateVendor 相关配置
                .packages("com.wk.entity")  // 数据库实体entity 所在包
                .persistenceUnit("orderPersistenceUnit")    // 设置持久单元的名字,需要唯一
                .build();
    }

    /**
     * 创建PlatformTransactionManager
     */
    @Bean(name = DBConstants.TX_MANAGER_ORDERS)
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}
