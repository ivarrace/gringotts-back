package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "accounting")
public class AccountingEntity {

    @Id
    private String id;
    private String key;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModified;
    private String name;
    private List<UserAccountingRoleEntity> users;
    private List<GroupEntity> groups;

}