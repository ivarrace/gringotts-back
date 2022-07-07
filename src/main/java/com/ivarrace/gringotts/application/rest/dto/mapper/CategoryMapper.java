package com.ivarrace.gringotts.application.rest.dto.mapper;

import com.ivarrace.gringotts.application.rest.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.rest.dto.response.CategoryResponse;
import com.ivarrace.gringotts.domain.model.Category;

import java.util.stream.Collectors;

public class CategoryMapper {

    private CategoryMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static CategoryResponse dtoToResponse(Category dto) {
        CategoryResponse response = new CategoryResponse();
        response.setId(dto.getId());
        response.setCreatedDate(dto.getCreatedDate());
        response.setName(dto.getName());
        response.setRecords(dto.getRecords().stream().map(RecordMapper::dtoToResponse).collect(Collectors.toList()));
        response.setAnnualTotals(AnnualSummaryGenerator.generateCategoryAnnualSummary(response));
        return response;
    }

    public static Category requestToDto(CategoryRequest request) {
        Category dto = new Category();
        dto.setName(request.getName());
        return dto;
    }
}
