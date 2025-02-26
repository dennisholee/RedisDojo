package io.forest.redis.springdemo.adapter.restapi.controllers;

import io.forest.redis.springdemo.adapter.restapi.ReportRestApi;
import io.forest.redis.springdemo.adapter.restapi.model.Report;
import io.forest.redis.springdemo.app.dto.ReportDTO;
import io.forest.redis.springdemo.app.in.FindReport;
import io.forest.redis.springdemo.app.in.FindReportCommand;
import io.forest.redis.springdemo.app.in.SaveReport;
import io.forest.redis.springdemo.app.in.SaveReportCommand;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReportRestController implements ReportRestApi {

    @NonNull
    FindReport findReport;

    @NonNull
    SaveReport saveReport;

    @Override
    public List<Report> getReports(String name) {
        log.info("Get report request [name={}]", name);

        return Optional.of(name)
                .map(FindReportCommand::new)
                .map(findReport::handle)
                .filter(Predicate.not(List::isEmpty))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .stream()
                .map(Report::of)
                .toList();
    }

    @Override
    public Report postReports(Report report) {
        log.info("Post report request [report={}]", report);

        return Optional.of(report)
                .map(ReportDTO::of)
                .map(SaveReportCommand::new)
                .map(saveReport::handle)
                .map(Report::of)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
