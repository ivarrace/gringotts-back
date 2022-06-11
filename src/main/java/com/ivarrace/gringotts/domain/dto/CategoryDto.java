package com.ivarrace.gringotts.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDto {

    private String id;
    private LocalDateTime createdDate;
    private String name;
    private List<RecordDto> records;

}
