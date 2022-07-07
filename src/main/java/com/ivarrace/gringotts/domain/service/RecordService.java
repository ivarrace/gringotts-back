package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.model.Category;
import com.ivarrace.gringotts.domain.model.Record;
import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final CategoryService categoryService;

    public RecordService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Record> findAll(String accountingId, String groupId, String categoryId) {
        return categoryService.findCategoryById(accountingId, groupId, categoryId).getRecords();
    }

    public Record findById(String accountingId, String groupId, String categoryId, String recordId) {
        return categoryService.findCategoryById(accountingId, groupId, categoryId)
                .getRecords().stream()
                .filter(record -> recordId.equals(record.getId()))
                .findAny().orElseThrow(() -> new ObjectNotFoundException(recordId));
    }

    public Accounting create(String accountingId, String groupId, String categoryId, Record request) {
        Category category = categoryService.findCategoryById(accountingId, groupId, categoryId);
        category.getRecords().add(request);
        return categoryService.modifyCategory(accountingId,groupId,categoryId,category);
    }

    public Accounting delete(String accountingId, String groupId, String categoryId, String recordId) {
        Category category = categoryService.findCategoryById(accountingId, groupId, categoryId);
        category.getRecords().stream().map(record -> !recordId.equals(record.getId()));
        return categoryService.modifyCategory(accountingId,groupId,categoryId,category);
    }

    public Accounting modify(String accountingId, String groupId, String categoryId
            , String recordId, Record request) {
        Category category = categoryService.findCategoryById(accountingId, groupId, categoryId);
        category.getRecords().stream().map(record -> !recordId.equals(record.getId()));
        category.getRecords().add(request);
        return categoryService.modifyCategory(accountingId,groupId,categoryId,category);
    }

}
