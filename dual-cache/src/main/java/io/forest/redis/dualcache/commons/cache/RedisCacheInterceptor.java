package io.forest.redis.dualcache.commons.cache;

import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.data.redis.cache.RedisCache;

import java.util.Optional;

@RequiredArgsConstructor
public class RedisCacheInterceptor extends CacheInterceptor {

    @NonNull
    CacheManager cacheManager;

    @Override
    protected Cache.ValueWrapper doGet(@Nonnull Cache cache, @NonNull Object key) {
        Cache.ValueWrapper superGetResult = super.doGet(cache, key);

        Optional.ofNullable(superGetResult)
                .ifPresent(
                        it -> {
                            if (cache.getClass() == RedisCache.class) {
                                Optional.of(cache.getName())
                                        .map(cacheManager::getCache)
                                        .ifPresent(value -> cache.putIfAbsent(key, value));
                            }
                        }

                );
        return superGetResult;
    }
}
