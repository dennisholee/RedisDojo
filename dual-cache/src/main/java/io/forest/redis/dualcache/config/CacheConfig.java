package io.forest.redis.dualcache.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.cache.CaffeineStatsCounter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.forest.redis.dualcache.commons.cache.CacheProperties;
import io.forest.redis.dualcache.commons.cache.RedisCacheInterceptor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

/**
 * https://docs.spring.io/spring-data/redis/reference/redis/redis-cache.html
 */
@Configuration
@EnableCaching
@ConfigurationProperties(prefix = "app.cache.config")
@ConfigurationPropertiesScan
@Setter
public class CacheConfig {

    List<CacheProperties> level1PropertiesList;

    List<CacheProperties> level2PropertiesList;

    @Bean("caffeineCacheManager")
    @Primary
    public CaffeineCacheManager caffeineCacheManager(MeterRegistry meterRegistry) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

            for (CacheProperties properties : level1PropertiesList) {
                cacheManager.registerCustomCache(
                        properties.getCacheName(),
                        Caffeine.newBuilder()
                                .expireAfterWrite(Duration.ofMinutes(properties.getTtlInMinutes()))
                                .initialCapacity(1)
                                .maximumSize(2000)
                                .recordStats(() -> new CaffeineStatsCounter(
                                        meterRegistry,
                                        properties.getCacheName()
                                ))
                                .build());
            }

        return cacheManager;
    }

    @Bean("redisCacheManager")
    RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory, MeterRegistry meterRegistry) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(connectionFactory);

        for(CacheProperties properties : level2PropertiesList) {
            builder.withCacheConfiguration(
                    properties.getCacheName(),
                    RedisCacheConfiguration.defaultCacheConfig()
                            .entryTtl(Duration.ofMinutes(properties.getTtlInMinutes()))
                            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                            .disableCachingNullValues()

            );
        }
        builder.enableStatistics();
        return builder.build();
    }

    @Bean
    RedisCacheInterceptor cacheInterceptor(CacheManager caffeineCacheManager, CacheOperationSource cacheOperationSource) {
        RedisCacheInterceptor interceptor = new RedisCacheInterceptor(caffeineCacheManager);
        interceptor.setCacheOperationSources(cacheOperationSource);
        return interceptor;
    }

    @Bean
    CacheOperationSource cacheOperationSource() {
        return new AnnotationCacheOperationSource();
    }

    @Bean
    SimpleMeterRegistry simpleMeterRegistry() {
        return new SimpleMeterRegistry();
    }
}
