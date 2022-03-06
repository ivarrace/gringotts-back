package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.request.CategoryRequest;
import com.ivarrace.gringotts.dto.response.CategoryResponse;
import com.ivarrace.gringotts.repository.model.Category;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
        dto.setRecords(recordMapper.toDto(entity.getRecords()));
        return dto;
    }

    public List<CategoryResponse> toDto(List<Category> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Category toNewEntity(CategoryRequest request) {
        Category entity = new Category();
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedDate(new Date());
        entity.setName(request.getName());
        entity.setRecords(Collections.emptyList());
        return entity;
    }
}
