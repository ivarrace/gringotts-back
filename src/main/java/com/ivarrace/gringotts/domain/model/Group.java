package com.ivarrace.gringotts.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Group {

    private String id;
    private LocalDateTime createdDate;
    private String name;
    private GroupType type;
    private List<Category> categories;

}
