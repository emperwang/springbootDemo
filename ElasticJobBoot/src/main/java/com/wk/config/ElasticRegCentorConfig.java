package com.wk.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticRegCentorConfig {
    @Autowired
    private ElasticParameter elasticParameter;

    /**
     *  zk 配置
     * @return
     */
    @Bean/*(initMethod = "init")*/
    public ZookeeperRegistryCenter registryCenter(){
        String zkList = elasticParameter.getZkList();
        String namespace = elasticParameter.getNamespace();
        ZookeeperRegistryCenter center = new ZookeeperRegistryCenter(new ZookeeperConfiguration(zkList, namespace));
        center.init();
        return center;
    }
}
