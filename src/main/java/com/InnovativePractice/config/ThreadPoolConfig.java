package com.InnovativePractice.config;
import java.util.concurrent.ThreadPoolExecutor;  // 这个导入语句是必需的

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    /**
     * 定义线程池
     */
    @Bean(name = "commonTaskExecutor")
    public ThreadPoolTaskExecutor commonTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 设置线程池的核心线程数
        executor.setCorePoolSize(5);

        // 设置最大线程数
        executor.setMaxPoolSize(10);

        // 设置队列容量
        executor.setQueueCapacity(25);

        // 设置线程池对拒绝任务的处理策略（当队列满，线程池已达到最大线程数时）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 初始化线程池
        executor.initialize();

        return executor;
    }
}
