package com.ivarrace.gringotts.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupDto {

    private String id;
    private LocalDateTime createdDate;
    private String name;
    private GroupTypeDto type;
    private List<CategoryDto> categories;

}
