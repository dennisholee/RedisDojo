package io.forest.redis.dualcache.adapter.restapi.models;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class Message {

    String message;
}
