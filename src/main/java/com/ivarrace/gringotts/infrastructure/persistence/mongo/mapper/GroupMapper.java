package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.domain.model.GroupType;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupEntity;

import java.util.stream.Collectors;

public class GroupMapper {

    private GroupMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static Group entityToDomain(GroupEntity entity){
        if(entity==null) return null;
        Group domain = new Group();
        domain.setId(entity.getId());
        domain.setCreatedDate(entity.getCreatedDate());
        domain.setName(entity.getName());
        domain.setType(GroupType.valueOf(entity.getType()));
        domain.setCategories(entity.getCategories().stream().map(CategoryMapper::entityToDomain).collect(Collectors.toList()));
        return domain;
    }

    public static GroupEntity domainToEntity(Group domain) {
        if(domain==null) return null;
        GroupEntity entity = new GroupEntity();
        entity.setId(domain.getId());
        entity.setCreatedDate(domain.getCreatedDate());
        entity.setName(domain.getName());
        entity.setType(domain.getType().name());
        entity.setCategories(domain.getCategories().stream().map(CategoryMapper::domainToEntity).collect(Collectors.toList()));
        return entity;
    }
}
