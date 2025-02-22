package io.forest.redis.springdemo.adapter.repository.redis.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import io.forest.redis.springdemo.app.dto.ReportDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Document
@Accessors(chain = true)
public class ReportEntity {

    @Id
    private Long id;

    @Indexed
    private String name;

    public static ReportEntity of(ReportDTO dto) {
        return new ReportEntity()
                .setId(dto.id())
                .setName(dto.name());
    }

}
