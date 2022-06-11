package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.dto.CategoryDto;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Category;

import java.util.stream.Collectors;

public class CategoryMapper {

    private CategoryMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static CategoryDto entityToDto(Category entity){
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setName(entity.getName());
        dto.setRecords(entity.getRecords().stream().map(RecordMapper::entityToDto).collect(Collectors.toList()));
        return dto;
    }

    public static Category dtoToEntity(CategoryDto dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setName(dto.getName());
        entity.setRecords(dto.getRecords().stream().map(RecordMapper::dtoToEntity).collect(Collectors.toList()));
        return entity;
    }
}
