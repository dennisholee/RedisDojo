package io.forest.redis.springdemo.app.dto;

import io.forest.redis.springdemo.adapter.repository.redis.model.ReportEntity;
import io.forest.redis.springdemo.adapter.restapi.model.Report;

public record ReportDTO(
        Long id,
        String name) {

    public static ReportDTO of(ReportEntity report) {
        return new ReportDTO(report.getId(), report.getName());
    }

    public static ReportDTO of(Report report) {
        return new ReportDTO((report.getId()), report.getName());
    }
}
