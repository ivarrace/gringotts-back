package com.ivarrace.gringotts.infrastructure.persistence.mongo.mapper;

import com.ivarrace.gringotts.domain.model.Record;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.RecordEntity;

public class RecordMapper {

    private RecordMapper(){
        throw new IllegalStateException("Utility class");
    }

    public static Record entityToDomain(RecordEntity entity){
        if(entity==null) return null;
        Record domain = new Record();
        domain.setId(entity.getId());
        domain.setDate(entity.getDate());
        domain.setAmount(entity.getAmount());
        domain.setInfo(entity.getInfo());
        return domain;
    }

    public static RecordEntity domainToEntity(Record domain) {
        if(domain==null) return null;
        RecordEntity entity = new RecordEntity();
        entity.setId(domain.getId());
        entity.setDate(domain.getDate());
        entity.setAmount(domain.getAmount());
        entity.setInfo(domain.getInfo());
        return entity;
    }
}
