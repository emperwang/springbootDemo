package com.wk.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class SimpleJobDemo implements SimpleJob{

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("getTaskId : " + shardingContext.getTaskId()+ ",   getJobName: " + shardingContext.getJobName()
        + ",   getJobParameter: " + shardingContext.getJobParameter()
        +",    getShardingParameter : " + shardingContext.getShardingParameter()
        + ",   getShardingItem : " + shardingContext.getShardingItem()
        + ",   getShardingTotalCount : " + shardingContext.getShardingTotalCount());
    }
}
