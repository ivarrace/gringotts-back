package com.ivarrace.gringotts.application.rest.dto.mapper;

import com.ivarrace.gringotts.application.rest.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.rest.dto.response.GroupResponse;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.domain.model.GroupType;

import java.util.stream.Collectors;

public class GroupMapper {

    private GroupMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static GroupResponse dtoToResponse(Group dto) {
        GroupResponse response = new GroupResponse();
        response.setId(dto.getId());
        response.setCreatedDate(dto.getCreatedDate());
        response.setName(dto.getName());
        response.setCategories(dto.getCategories().stream().map(CategoryMapper::dtoToResponse).collect(Collectors.toList()));
        response.setAnnualTotals(AnnualSummaryGenerator.generateGroupAnnualSummary(response));
        response.setType(GroupType.valueOf(dto.getType().name()));
        return response;
    }

    public static Group requestToDto(GroupRequest request) {
        Group dto = new Group();
        dto.setName(request.getName());
        return dto;
    }

}
