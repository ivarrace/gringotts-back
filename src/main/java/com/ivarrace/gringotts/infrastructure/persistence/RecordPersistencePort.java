package com.ivarrace.gringotts.infrastructure.persistence;

import com.ivarrace.gringotts.domain.model.Record;

import java.util.List;
import java.util.Optional;

public interface RecordPersistencePort {

    Optional<Record> findById(String accountingId, String groupId, String categoryId, String recordId);

    List<Record> findAll(String accountingId, String groupId, String categoryId);

    Record save(String accountingId, String groupId, String categoryId, Record record);

    void delete(String accountingId, String groupId, String categoryId, String recordId);
}
