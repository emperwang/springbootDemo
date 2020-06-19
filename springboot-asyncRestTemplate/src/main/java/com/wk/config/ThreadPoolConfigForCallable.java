package com.wk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfigForCallable {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setMaxPoolSize(80);
        taskExecutor.setQueueCapacity(200);
        taskExecutor.setThreadNamePrefix("Async-service");
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());;
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     *  此配置用于设置一个springmvc异步执行请求时的线程池
     *  当controller Callable 和 WebAsyncTask，而没有配置线程池时，会报下面的警告:
           !!!
         An Executor is required to handle java.util.concurrent.Callable return values.
         Please, configure a TaskExecutor in the MVC config under "async support".
         The SimpleAsyncTaskExecutor currently in use is not suitable under load.
         -------------------------------
         Request URI: '/webasyn.do'
         !!!

     添加后，警告就消失了
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
                configurer.setTaskExecutor(taskExecutor());
            }
        };
    }

    /**
     *  第二种配置 springmvc的线程池
     * @return
     */
    public WebMvcConfigurer anotherConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
                configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(50)));
            }
        };
    }
}
