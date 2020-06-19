package com.wk.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.wk.elasticjob.JobListener;
import com.wk.elasticjob.SimpleJobDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticJobConfig {
    @Autowired
    private ElasticParameter elasticParameter;
    @Autowired
    private ZookeeperRegistryCenter center;
    @Autowired
    private SimpleJobDemo simpleJobDemo;
    @Autowired
    private JobListener jobListener;

    public LiteJobConfiguration liteJobConfiguration(Class<? extends SimpleJob> jobClass,String cron,
                                                     Integer shardingTotalCount,String shardingItemParameter){
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameter).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(simpleJobConfiguration).overwrite(true).build();

        return liteJobConfiguration;
    }
    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(){
        String stockCron = elasticParameter.getStockCron();
        int stockShardingTotalCount = elasticParameter.getStockShardingTotalCount();
        String shardingItemParamrters = elasticParameter.getShardingItemParamrters();
        LiteJobConfiguration liteJobConfiguration = liteJobConfiguration(simpleJobDemo.getClass(), stockCron, stockShardingTotalCount, shardingItemParamrters);
        SpringJobScheduler scheduler = new SpringJobScheduler(simpleJobDemo, center, liteJobConfiguration, jobListener);

        return scheduler;
    }
}















