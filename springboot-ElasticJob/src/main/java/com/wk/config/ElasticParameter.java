package com.wk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  elasticjob 需要的参数
 */
@Component
public class ElasticParameter {
    @Value("${regCenter.serverList}")
    private String zkList;
    @Value("${regCenter.namespace}")
    private String namespace;
    @Value("${stock.cron}")
    private String stockCron;
    @Value("${stock.shardingTotalCount}")
    private int stockShardingTotalCount;
    @Value("${stock.shardingItemParamrters}")
    private String shardingItemParamrters;

    public String getZkList() {
        return zkList;
    }

    public void setZkList(String zkList) {
        this.zkList = zkList;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getStockCron() {
        return stockCron;
    }

    public void setStockCron(String stockCron) {
        this.stockCron = stockCron;
    }

    public int getStockShardingTotalCount() {
        System.out.println("stockShardingTotalCount : "+stockShardingTotalCount);
        return stockShardingTotalCount;
    }

    public void setStockShardingTotalCount(int stockShardingTotalCount) {
        this.stockShardingTotalCount = stockShardingTotalCount;
    }

    public String getShardingItemParamrters() {
        return shardingItemParamrters;
    }

    public void setShardingItemParamrters(String shardingItemParamrters) {
        this.shardingItemParamrters = shardingItemParamrters;
    }
}
