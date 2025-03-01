package io.forest.redis.dualcache.app;

import io.forest.redis.dualcache.app.command.GreetCommand;
import io.forest.redis.dualcache.app.dto.MessageDTO;

public interface Greet {

    MessageDTO sayHello(GreetCommand command);
}
