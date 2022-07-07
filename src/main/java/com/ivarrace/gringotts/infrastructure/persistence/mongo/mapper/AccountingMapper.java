package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingEntity;

import java.util.stream.Collectors;

public class AccountingMapper {

    private AccountingMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static Accounting entityToDomain(AccountingEntity entity){
        if(entity==null) return null;
        Accounting domain = new Accounting();
        domain.setId(entity.getId());
        domain.setKey(entity.getKey());
        domain.setName(entity.getName());
        domain.setCreatedDate(entity.getCreatedDate());
        domain.setLastModified(entity.getLastModified());
        domain.setUsers(entity.getUsers().stream().map(UserAccountingRoleMapper::entityToDomain).collect(Collectors.toList()));
        domain.setGroups(entity.getGroups().stream().map(GroupMapper::entityToDomain).collect(Collectors.toList()));
        return domain;
    }

    public static AccountingEntity domainToEntity(Accounting domain){
        if(domain==null) return null;
        AccountingEntity entity = new AccountingEntity();
        entity.setId(domain.getId());
        entity.setKey(domain.getKey());
        entity.setName(domain.getName());
        entity.setCreatedDate(domain.getCreatedDate());
        entity.setLastModified(domain.getLastModified());
        entity.setUsers(domain.getUsers().stream().map(UserAccountingRoleMapper::domainToEntity).collect(Collectors.toList()));
        entity.setGroups(domain.getGroups().stream().map(GroupMapper::domainToEntity).collect(Collectors.toList()));
        return entity;
    }
}
