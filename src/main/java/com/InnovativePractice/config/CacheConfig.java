package com.InnovativePractice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // 不需要额外的 Redis 配置，Spring 会自动处理
}
