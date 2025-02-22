package io.forest.redis.springdemo.app.in;

import io.forest.redis.springdemo.app.dto.ReportDTO;

import java.util.List;

public interface FindReport {
    List<ReportDTO> handle(FindReportCommand command);
}
