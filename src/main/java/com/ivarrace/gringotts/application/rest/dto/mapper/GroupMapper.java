package com.ivarrace.gringotts.application.dto.mapper;

import com.ivarrace.gringotts.application.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.dto.response.GroupResponse;
import com.ivarrace.gringotts.application.dto.DtoUtils;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Group;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    private final CategoryMapper categoryMapper;

    public GroupMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public GroupResponse toDto(Group entity) {
        GroupResponse dto = new GroupResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCategories(categoryMapper.toDtoList(entity.getCategories()));
        dto.setType(entity.getType());
        return dto;
    }

    public List<GroupResponse> toDtoList(List<Group> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Group toNewEntity(GroupType groupType, GroupRequest request) {
        Group entity = new Group();
        entity.setId(DtoUtils.generateKey(request.getName()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setName(request.getName());
        entity.setCategories(Collections.emptyList());
        entity.setType(groupType);
        return entity;
    }

}
