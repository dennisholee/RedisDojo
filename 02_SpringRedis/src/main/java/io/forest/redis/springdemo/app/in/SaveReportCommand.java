package io.forest.redis.springdemo.app.in;

import io.forest.redis.springdemo.app.dto.ReportDTO;

public record SaveReportCommand(
    ReportDTO reportDTO) {
}
