package io.forest.redis.springdemo.app;

import io.forest.redis.springdemo.app.dto.ReportDTO;
import io.forest.redis.springdemo.app.in.SaveReport;
import io.forest.redis.springdemo.app.out.ReportRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveReportApp implements SaveReport {

    @NonNull
    ReportRepository repository;

    @Override
    public ReportDTO handle(ReportDTO reportDTO) {
        log.info("Save report to repository [reportDTO={}]", reportDTO);

        return this.repository.save(reportDTO);
    }
}
