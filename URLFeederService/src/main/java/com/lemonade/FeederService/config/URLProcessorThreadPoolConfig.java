package com.lemonade.FeederService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class URLProcessorThreadPoolConfig {

    /**
     * executes URL bean
     */
    @Bean(name = "urlProcessor")
    public Executor urlExecutorBean(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // *note - need to import this numbers from properties and not hard code like this!
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("url-processor-thread-");
        executor.initialize();
        return executor;
    }

}
