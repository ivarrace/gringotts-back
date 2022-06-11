package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.dto.RecordDto;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Record;

public class RecordMapper {

    private RecordMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static RecordDto entityToDto(Record entity){
        RecordDto dto = new RecordDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());
        dto.setInfo(entity.getInfo());
        return dto;
    }

    public static Record dtoToEntity(RecordDto dto) {
        Record entity = new Record();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setAmount(dto.getAmount());
        entity.setInfo(dto.getInfo());
        return entity;
    }
}
