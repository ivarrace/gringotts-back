package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryEntity {

    private String id;
    @CreatedDate
    private LocalDateTime createdDate;
    private String name;
    private List<RecordEntity> records;

}
