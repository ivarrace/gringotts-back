package com.ivarrace.gringotts.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccountingDto {
    private String id;
    private String key;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private String name;
    private List<UserAccountingRoleDto> users;
    private List<GroupDto> groups;
}
