package com.ivarrace.gringotts.application.dto.mapper;

import com.ivarrace.gringotts.application.dto.request.RecordRequest;
import com.ivarrace.gringotts.application.dto.response.RecordResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Record;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RecordMapper {

    public RecordResponse toDto(Record entity) {
        RecordResponse dto = new RecordResponse();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());
        dto.setInfo(entity.getInfo());
        return dto;
    }

    public List<RecordResponse> toDtoList(List<Record> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Record toNewEntity(RecordRequest request) {
        Record entity = new Record();
        entity.setId(UUID.randomUUID().toString());
        entity.setDate(request.getDate());
        entity.setAmount(request.getAmount());
        entity.setInfo(request.getInfo());
        return entity;
    }
}
