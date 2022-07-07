package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.model.AccountingRole;
import com.ivarrace.gringotts.domain.model.UserAccountingRole;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserAccountingRoleEntity;

public class UserAccountingRoleMapper {

    private UserAccountingRoleMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static UserAccountingRole entityToDomain(UserAccountingRoleEntity entity){
        if(entity==null) return null;
        UserAccountingRole domain = new UserAccountingRole(entity.getUserId(), AccountingRole.valueOf(entity.getRole()));
        return domain;
    }

    public static UserAccountingRoleEntity domainToEntity(UserAccountingRole domain) {
        if(domain==null) return null;
        UserAccountingRoleEntity entity = new UserAccountingRoleEntity();
        entity.setUserId(domain.getUserId());
        entity.setRole(domain.getRole().name());
        return entity;
    }
}
