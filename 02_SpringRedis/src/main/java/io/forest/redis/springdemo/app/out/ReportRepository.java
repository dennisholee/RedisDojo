package io.forest.redis.springdemo.app.out;

import io.forest.redis.springdemo.app.dto.ReportDTO;

import java.util.List;

public interface ReportRepository {

    List<ReportDTO> findByName(String name);

    ReportDTO save(ReportDTO report);
}
