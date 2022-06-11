package com.ivarrace.gringotts.application.dto.mapper;

import com.ivarrace.gringotts.application.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.dto.DtoUtils;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    private final RecordMapper recordMapper;

    public CategoryMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    public CategoryResponse toDto(Category entity) {
        CategoryResponse dto = new CategoryResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRecords(recordMapper.toDtoList(entity.getRecords()));
        return dto;
    }

    public List<CategoryResponse> toDtoList(List<Category> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Category toNewEntity(CategoryRequest request) {
        Category entity = new Category();
        entity.setId(DtoUtils.generateKey(request.getName()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setName(request.getName());
        entity.setRecords(Collections.emptyList());
        return entity;
    }
}
