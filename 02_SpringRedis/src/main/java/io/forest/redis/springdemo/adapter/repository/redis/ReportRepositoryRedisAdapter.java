package io.forest.redis.springdemo.adapter.repository.redis;

import io.forest.redis.springdemo.adapter.repository.redis.model.ReportEntity;
import io.forest.redis.springdemo.app.dto.ReportDTO;
import io.forest.redis.springdemo.app.out.ReportRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportRepositoryRedisAdapter implements ReportRepository {

    @NonNull
    ReportRedisDocumentRepository repository;

    @Override
    public List<ReportDTO> findByName(String name) {
        log.info("Search Redis repository [name={}]", name);

        return repository.findByName(name)
                .stream()
                .map(ReportDTO::of)
                .toList();
    }

    public ReportDTO save(ReportDTO dto) {
        log.info("Save report to Redis repository [dto={}]", dto);

        return Optional.of(dto)
                .map(ReportEntity::of)
                .map(repository::save)
                .map(ReportDTO::of)
                .orElseThrow();
    }
}
