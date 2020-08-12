package com.wk.config;

import com.wk.factory.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/*@Configuration
@EnableAsync*/
public class ExecutorConfig {

    //@Bean("executor")
    @Bean("taskExecutor")
    public Executor executor(){
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("custome-fcatory-name-%d").setDaemon(true)
                .build();
        int process = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(process, Integer.MAX_VALUE, 50, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), factory);

        return executor;
    }
}
