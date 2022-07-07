package com.ivarrace.gringotts.application.rest.dto.mapper;

import com.ivarrace.gringotts.application.rest.dto.request.RecordRequest;
import com.ivarrace.gringotts.application.rest.dto.response.RecordResponse;
import com.ivarrace.gringotts.domain.model.Record;

public class RecordMapper {

    private RecordMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static RecordResponse dtoToResponse(Record dto) {
        RecordResponse response = new RecordResponse();
        response.setId(dto.getId());
        response.setDate(dto.getDate());
        response.setAmount(dto.getAmount());
        response.setInfo(dto.getInfo());
        return response;
    }

    public static Record requestToDto(RecordRequest request) {
        Record dto = new Record();
        dto.setDate(request.getDate());
        dto.setAmount(request.getAmount());
        dto.setInfo(request.getInfo());
        return dto;
    }
}
