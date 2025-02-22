package io.forest.redis.springdemo.adapter.repository.redis;

import com.redis.om.spring.repository.RedisDocumentRepository;
import io.forest.redis.springdemo.adapter.repository.redis.model.ReportEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRedisDocumentRepository extends RedisDocumentRepository<ReportEntity, Long> {
    List<ReportEntity> findByName(String name);
}
