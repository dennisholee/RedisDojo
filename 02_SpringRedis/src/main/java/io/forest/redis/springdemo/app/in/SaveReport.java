package io.forest.redis.springdemo.app.in;

import io.forest.redis.springdemo.app.dto.ReportDTO;

public interface SaveReport {

    ReportDTO handle(SaveReportCommand command);
}
