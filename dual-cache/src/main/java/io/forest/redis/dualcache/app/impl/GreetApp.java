package io.forest.redis.dualcache.app.impl;

import io.forest.redis.dualcache.app.Greet;
import io.forest.redis.dualcache.app.command.GreetCommand;
import io.forest.redis.dualcache.app.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GreetApp implements Greet {

    @Caching(cacheable = {
            @Cacheable(cacheNames = "GreetL1", cacheManager = "caffeineCacheManager"),
            @Cacheable(cacheNames = "GreetL2", cacheManager = "redisCacheManager")
    })
    @Override
    public MessageDTO sayHello(GreetCommand command) {
        return new MessageDTO("Hello %s".formatted(command.getMessage()));
    }
}
