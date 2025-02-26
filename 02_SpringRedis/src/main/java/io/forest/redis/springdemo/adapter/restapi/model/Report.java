package io.forest.redis.springdemo.adapter.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.forest.redis.springdemo.app.dto.ReportDTO;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;


@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class Report {

    Long id;

    String name;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public Report setId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public Report setName(String name) {
        this.name = name;
        return this;
    }

    public static Report of(ReportDTO dto) {
        return new Report()
                .setId(dto.id())
                .setName(dto.name());
    }
}
