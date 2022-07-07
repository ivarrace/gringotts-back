package com.ivarrace.gringotts.infrastructure.persistence.mongo;

import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.domain.model.Group;
import com.ivarrace.gringotts.domain.model.Record;
import com.ivarrace.gringotts.infrastructure.persistence.CategoryPersistencePort;
import com.ivarrace.gringotts.infrastructure.persistence.RecordPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecordMongoPersistenceAdapter implements RecordPersistencePort {

    @Autowired
    private CategoryPersistencePort categoryPersistencePort;

    @Override
    public Optional<Record> findById(String accountingId, String groupId, String categoryId, String recordId) {
        Optional<Category> category = categoryPersistencePort.findById(accountingId, groupId, categoryId);
        if(category.isPresent()){
            Record record = category.get().getRecords().stream()
                    .filter(entity -> entity.getId().equals(recordId))
                    .findFirst().orElseThrow(() -> new ObjectNotFoundException(recordId));
            return Optional.of(record);
        }
        return Optional.empty();
    }

    @Override
    public List<Record> findAll(String accountingId, String groupId, String categoryId) {
        Optional<Category> category = categoryPersistencePort.findById(accountingId, groupId, categoryId);
        if(category.isPresent()){
            return category.get().getRecords();
        }
        return Collections.emptyList();
    }

    @Override
    public Record save(String accountingId, String groupId, String categoryId, Record record) {
        Optional<Category> category = categoryPersistencePort.findById(accountingId, groupId, categoryId);
        if(category.isPresent()){
            Category modify = category.get();
            modify.getRecords().add(record);
            categoryPersistencePort.save(accountingId, groupId, modify);
            return record;
        }
        return null;
    }

    @Override
    public void delete(String accountingId, String groupId, String categoryId, String recordId) {
        Optional<Category> category = categoryPersistencePort.findById(accountingId, groupId, categoryId);
        if(category.isPresent()){
            Category modify = category.get();
            List<Record> newRecords =
                    modify.getRecords().stream().filter(entity -> !entity.getId().equals(recordId)).collect(Collectors.toList());
            modify.setRecords(newRecords);
            categoryPersistencePort.save(accountingId, groupId, modify);
        }
    }
}
