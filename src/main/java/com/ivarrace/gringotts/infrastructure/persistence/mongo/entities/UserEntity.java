package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModified;
    @Indexed(unique=true)
    private String username;
    private String password;
    private Set<String> authorities = new HashSet<>();
    private boolean enabled = true;
    private boolean nonExpired = true;
    private boolean nonLocked = true;
    private boolean credentialNonExpired = true;

}
