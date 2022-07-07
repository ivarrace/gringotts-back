package com.ivarrace.gringotts.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Accounting {
    private String id;
    private String key;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
    private String name;
    private List<UserAccountingRole> users;
    private List<Group> groups;
}
