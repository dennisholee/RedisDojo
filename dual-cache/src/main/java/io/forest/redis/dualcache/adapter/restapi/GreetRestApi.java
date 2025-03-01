package io.forest.redis.dualcache.adapter.restapi;

import io.forest.redis.dualcache.adapter.restapi.models.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface GreetRestApi {

    @Operation(
            summary = "Greet with specified message",
            description = "Greet with specified message",
            tags = "Greet",
            operationId = "getGreetMessage",
            responses = {
                    @ApiResponse(
                            description = "List of reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Message.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/hello/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
    Message getGreetMessage(@PathVariable("message") String message);
}
