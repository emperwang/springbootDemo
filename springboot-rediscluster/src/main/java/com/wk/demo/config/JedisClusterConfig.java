package com.wk.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisClusterConfig {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisCluster clusterFactory(){
        String[] split = redisConfig.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String host : split) {
            String[] hostIP = host.split(":");
            nodes.add(new HostAndPort(hostIP[0],Integer.parseInt(hostIP[1])));
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getMaxIdle());
        poolConfig.setMinIdle(redisConfig.getMinIdle());
        poolConfig.setMaxTotal(redisConfig.getMaxActive());
        poolConfig.setMinIdle(redisConfig.getMinIdle());
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEviction());
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMaxWaitMillis(10000);
        JedisCluster cluster = new JedisCluster(nodes, redisConfig.getConnectionTimeOut(), poolConfig);
        return cluster;
    }
}
