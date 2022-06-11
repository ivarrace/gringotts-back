package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.dto.AccountingRoleDto;
import com.ivarrace.gringotts.domain.dto.UserAccountingRoleDto;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRole;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.UserAccountingRole;

public class UserAccountingRoleMapper {

    private UserAccountingRoleMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static UserAccountingRoleDto entityToDto(UserAccountingRole entity){
        UserAccountingRoleDto dto = new UserAccountingRoleDto();
        dto.setUserId(entity.getUserId());
        dto.setRole(AccountingRoleDto.valueOf(entity.getRole().name()));
        return dto;
    }

    public static UserAccountingRole dtoToEntity(UserAccountingRoleDto dto) {
        UserAccountingRole entity = new UserAccountingRole();
        entity.setUserId(dto.getUserId());
        entity.setRole(AccountingRole.valueOf(dto.getRole().name()));
        return entity;
    }
}
