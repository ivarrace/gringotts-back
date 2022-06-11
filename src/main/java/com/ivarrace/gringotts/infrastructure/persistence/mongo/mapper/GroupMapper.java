package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.dto.GroupDto;
import com.ivarrace.gringotts.domain.dto.GroupTypeDto;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Group;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;

import java.util.stream.Collectors;

public class GroupMapper {

    private GroupMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static GroupDto entityToDto(Group entity){
        GroupDto dto = new GroupDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setName(entity.getName());
        dto.setType(GroupTypeDto.valueOf(entity.getType().name()));
        dto.setCategories(entity.getCategories().stream().map(CategoryMapper::entityToDto).collect(Collectors.toList()));
        return dto;
    }

    public static Group dtoToEntity(GroupDto dto) {
        Group entity = new Group();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setName(dto.getName());
        entity.setType(GroupType.valueOf(dto.getType().name()));
        entity.setCategories(dto.getCategories().stream().map(CategoryMapper::dtoToEntity).collect(Collectors.toList()));
        return entity;
    }
}
