package com.wk.config;

import com.wk.factory.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ExecutorConfig {

    @Bean("executor")
    public Executor executor(){
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("name-%d").setDaemon(true)
                .build();
        int process = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(process, Integer.MAX_VALUE, 50, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), factory);

        return executor;
    }
}
