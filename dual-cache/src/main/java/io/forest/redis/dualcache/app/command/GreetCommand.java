package io.forest.redis.dualcache.app.command;

import io.forest.redis.dualcache.app.dto.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Function;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class GreetCommand {

    String message;

    public MessageDTO handle(Function<GreetCommand, MessageDTO> func) {
        return func.apply(this);
    }

}
