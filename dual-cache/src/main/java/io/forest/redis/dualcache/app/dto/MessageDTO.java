package io.forest.redis.dualcache.app.dto;


import java.io.Serializable;

public record MessageDTO (
        String message
) implements Serializable  {
}
