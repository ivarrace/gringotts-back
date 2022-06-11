package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.application.dto.mapper.CategoryMapper;
import com.ivarrace.gringotts.application.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.application.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.dto.response.AccountingResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.AccountingRepository;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Accounting;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Category;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final AccountingRepository accountingRepository;
    private final CategoryMapper categoryMapper;
    private final AccountingMapper accountingMapper;
    private final AccountingUtils accountingUtils;

    public CategoryService(AccountingRepository accountingRepository,
                           CategoryMapper categoryMapper,
                           AccountingMapper accountingMapper,
                           AccountingUtils accountingUtils) {
        this.accountingRepository = accountingRepository;
        this.categoryMapper = categoryMapper;
        this.accountingMapper = accountingMapper;
        this.accountingUtils = accountingUtils;
    }

    public List<CategoryResponse> findAllCategories(String accountingId, String groupId) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        return categoryMapper.toDtoList(accountingUtils.findAccountingGroup(accounting, groupId).getCategories());
    }

    public CategoryResponse findCategoryById(String accountingId, String groupId, String categoryId) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        return categoryMapper.toDto(accountingUtils.findAccountingCategory(accounting, groupId, categoryId));
    }

    public AccountingResponse createCategory(String accountingId, String groupId, CategoryRequest categoryRequest) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        Group group = accountingUtils.findAccountingGroup(accounting, groupId);
        Category category = categoryMapper.toNewEntity(categoryRequest);
        group.getCategories().add(category);
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse deleteCategoryById(String accountingId, String groupId, String categoryId) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        Group group = accountingUtils.findAccountingGroup(accounting, groupId);
        accountingUtils.findAccountingCategory(accounting, groupId, categoryId);
        group.getCategories().removeIf(item -> categoryId.equals(item.getId()));
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse modifyCategory(String accountingId, String groupId, String categoryId,
                                     CategoryRequest categoryRequest) {
        Accounting accounting = accountingUtils.findAccountingEntityByKey(accountingId);
        Category category = accountingUtils.findAccountingCategory(accounting, groupId, categoryId);
        category.setName(categoryRequest.getName());
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

}
