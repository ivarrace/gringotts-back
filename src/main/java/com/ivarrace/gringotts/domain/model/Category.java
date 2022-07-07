package com.ivarrace.gringotts.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Category {

    private String id;
    private LocalDateTime createdDate;
    private String name;
    private List<Record> records;

}
