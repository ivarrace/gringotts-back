package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.request.GroupRequest;
import com.ivarrace.gringotts.dto.response.GroupResponse;
import com.ivarrace.gringotts.repository.model.Group;
import com.ivarrace.gringotts.repository.model.GroupType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedDate(new Date());
        entity.setName(request.getName());
        entity.setCategories(Collections.emptyList());
        entity.setType(groupType);
        return entity;
    }

}
