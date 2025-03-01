package io.forest.redis.dualcache.adapter.restapi.controllers;

import io.forest.redis.dualcache.adapter.restapi.GreetRestApi;
import io.forest.redis.dualcache.adapter.restapi.models.Message;
import io.forest.redis.dualcache.app.Greet;
import io.forest.redis.dualcache.app.command.GreetCommand;
import io.forest.redis.dualcache.app.dto.MessageDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class GreetController implements GreetRestApi {

    @NonNull
    Greet greet;

    @Override
    public Message getGreetMessage(String message) {
        return Optional.ofNullable(message)
                .map(GreetCommand::new)
                .map(it -> it.handle(greet::sayHello))
                .map(toMessage)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    Function<MessageDTO, Message> toMessage = dto ->
        new Message().setMessage(dto.message());
}
