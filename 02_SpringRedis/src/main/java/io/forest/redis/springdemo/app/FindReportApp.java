package io.forest.redis.springdemo.app;

import io.forest.redis.springdemo.app.dto.ReportDTO;
import io.forest.redis.springdemo.app.in.FindReport;
import io.forest.redis.springdemo.app.in.FindReportCommand;
import io.forest.redis.springdemo.app.out.ReportRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindReportApp implements FindReport {

    @NonNull
    ReportRepository reportRepository;

    @Override
    public List<ReportDTO> handle(FindReportCommand command) {
        log.info("Search report repository [command={}]", command);

        return reportRepository.findByName(command.name());
    }
}
