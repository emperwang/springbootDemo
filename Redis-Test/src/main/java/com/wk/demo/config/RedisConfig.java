package com.wk.demo.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ToString
// 把配置从application.yml中保存下来，已被后用
public class RedisConfig {
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.timeout}")
    private Integer connectionTimeOut;
    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWait;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;
    @Value("${spring.redis.jedis.pool.time-between-eviction-runs}")
    private Integer timeBetweenEviction;


}
