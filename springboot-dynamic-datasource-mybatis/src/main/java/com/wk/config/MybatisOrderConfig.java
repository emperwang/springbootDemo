package com.wk.config;

import com.wk.common.DBConstants;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.wk.mapper.order"},sqlSessionTemplateRef = "orderSqlSessionTemplate",sqlSessionFactoryRef = "orderSqlSessionFactory")
public class MybatisOrderConfig {

    @Bean(name="orderDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.orders")
    public DataSource dataSource(){
      //  return DataSourceBuilder.create().build();
        DataSource dataSource = DataSourceBuilder.create().driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://127.0.0.1:3306/test_orders?useSSL=false&useUnicode=true&characterEncoding=UTF-8")
                .username("root")
                .password("admin").build();
        return dataSource;
    }

    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 设置orders 数据源
        bean.setDataSource(this.dataSource());
        // 设置entity 包
        // bean.setTypeAliasesPackage("com.wk.entity");
        // 设置 config 路径
        //bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml));
        // 设置mapper 路径
        // 注意此处设置时  使用的 Resources
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/order/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "orderSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(this.sqlSessionFactory());
    }

    @Bean(name = DBConstants.TX_MANAGER_ORDERS)
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }
}
