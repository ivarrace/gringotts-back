package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;

@Data
public class UserAccountingRole {

    private String userId;
    private String role;

}