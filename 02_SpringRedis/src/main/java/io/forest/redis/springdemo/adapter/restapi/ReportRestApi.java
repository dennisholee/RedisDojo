package io.forest.redis.springdemo.adapter.restapi;

import io.forest.redis.springdemo.adapter.restapi.model.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReportRestApi {

    @Operation(
            summary = "Get list of reports",
            description = "Get list of reports",
            tags = "Reports",
            responses = {
                    @ApiResponse(
                            description = "List of reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Report> getReports(@RequestParam("name") String name);

    @Operation(
            summary = "Save report",
            description = "Save report",
            tags = "Reports",

            responses = {
                    @ApiResponse(
                            description = "List of reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
            }
    )
    @PostMapping(value = "/reports",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Report postReports(@RequestBody Report report);
}
