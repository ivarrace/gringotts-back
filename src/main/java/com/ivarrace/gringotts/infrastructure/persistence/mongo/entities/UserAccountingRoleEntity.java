package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;

@Data
public class UserAccountingRoleEntity {

    private String userId;
    private String role;

}