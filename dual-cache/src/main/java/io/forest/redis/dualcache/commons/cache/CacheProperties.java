package io.forest.redis.dualcache.commons.cache;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CacheProperties {

    String cacheName;

    boolean allowNullValues;

    long ttlInMinutes;
}
