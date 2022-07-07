package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.CategoryEntity;

import java.util.stream.Collectors;

public class CategoryMapper {

    private CategoryMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static Category entityToDomain(CategoryEntity entity){
        if(entity==null) return null;
        Category domain = new Category();
        domain.setId(entity.getId());
        domain.setCreatedDate(entity.getCreatedDate());
        domain.setName(entity.getName());
        domain.setRecords(entity.getRecords().stream().map(RecordMapper::entityToDomain).collect(Collectors.toList()));
        return domain;
    }

    public static CategoryEntity domainToEntity(Category domain) {
        if(domain==null) return null;
        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId());
        entity.setCreatedDate(domain.getCreatedDate());
        entity.setName(domain.getName());
        entity.setRecords(domain.getRecords().stream().map(RecordMapper::domainToEntity).collect(Collectors.toList()));
        return entity;
    }
}
