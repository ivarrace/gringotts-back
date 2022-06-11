package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.dto.AccountingDto;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Accounting;

import java.util.stream.Collectors;

public class AccountingMapper {

    private AccountingMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static AccountingDto entityToDto(Accounting entity){
        AccountingDto dto = new AccountingDto();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModified(entity.getLastModified());
        dto.setUsers(entity.getUsers().stream().map(UserAccountingRoleMapper::entityToDto).collect(Collectors.toList()));
        dto.setGroups(entity.getGroups().stream().map(GroupMapper::entityToDto).collect(Collectors.toList()));
        return dto;
    }

    public static Accounting dtoToEntity(AccountingDto dto){
        Accounting entity = new Accounting();
        entity.setId(dto.getId());
        entity.setKey(dto.getKey());
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setLastModified(dto.getLastModified());
        entity.setUsers(dto.getUsers().stream().map(UserAccountingRoleMapper::dtoToEntity).collect(Collectors.toList()));
        entity.setGroups(dto.getGroups().stream().map(GroupMapper::dtoToEntity).collect(Collectors.toList()));
        return entity;
    }
}
