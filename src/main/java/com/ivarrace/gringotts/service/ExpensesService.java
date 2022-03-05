package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingCategory;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesService {

    private final AccountingRepository accountingRepository;

    public ExpensesService(AccountingRepository accountingRepository) {
        this.accountingRepository = accountingRepository;
    }

    public List<AccountingGroup> findAll(String accountingId) {
        return findAccountingEntity(accountingId).getExpenses();
    }

    public AccountingGroup findById(String accountingId, String groupId) {
        Accounting accounting = findAccountingEntity(accountingId);
        return findAccountingGroup(accounting, groupId);
    }

    public Accounting create(String accountingId, AccountingGroup group) {
        Accounting actual = findAccountingEntity(accountingId);
        group.setId(UUID.randomUUID().toString());
        group.setCreatedDate(new Date());
        group.setCategories(Collections.emptyList());
        actual.getExpenses().add(group);
        return accountingRepository.save(actual);
    }

    public Accounting deleteById(String accountingId, String groupId) {
        Accounting accounting = findAccountingEntity(accountingId);
        findAccountingGroup(accounting, groupId);
        accounting.getExpenses().removeIf(group -> groupId.equals(group.getId()));
        return accountingRepository.save(accounting);
    }

    public Accounting modify(String accountingId, String groupId, AccountingGroup newGroup) {
        Accounting accounting = findAccountingEntity(accountingId);
        AccountingGroup actualGroup = findAccountingGroup(accounting, groupId);
        actualGroup.setName(newGroup.getName());
        return accountingRepository.save(accounting);
    }

    public List<AccountingCategory> findAllCategories(String accountingId, String groupId) {
        Accounting accounting = findAccountingEntity(accountingId);
        return findAccountingGroup(accounting, groupId).getCategories();
    }

    public AccountingCategory findCategoryById(String accountingId, String groupId, String categoryId) {
        Accounting accounting = findAccountingEntity(accountingId);
        return findAccountingCategory(accounting, groupId, categoryId);
    }

    public Accounting createCategory(String accountingId, String groupId, AccountingCategory category) {
        Accounting accounting = findAccountingEntity(accountingId);
        AccountingGroup group = findAccountingGroup(accounting, groupId);
        category.setId(UUID.randomUUID().toString());
        category.setCreatedDate(new Date());
        category.setRecords(Collections.emptyList());
        group.getCategories().add(category);
        return accountingRepository.save(accounting);
    }

    public Accounting deleteCategoryById(String accountingId, String groupId, String categoryId) {
        Accounting accounting = findAccountingEntity(accountingId);
        AccountingGroup group = findAccountingGroup(accounting, groupId);
        findAccountingCategory(accounting, groupId, categoryId);
        group.getCategories().removeIf(item -> categoryId.equals(item.getId()));
        return accountingRepository.save(accounting);
    }

    public Accounting modifyCategory(String accountingId, String groupId, String categoryId,
                                     AccountingCategory newCategory) {
        Accounting accounting = findAccountingEntity(accountingId);
        AccountingCategory category = findAccountingCategory(accounting, groupId, categoryId);
        category.setName(newCategory.getName());
        return accountingRepository.save(accounting);
    }

    private Accounting findAccountingEntity(String accountingId) {
        return accountingRepository.findById(accountingId)
                .orElseThrow(() -> new ObjectNotFoundException("Accounting[" + accountingId + "]"));
    }

    private AccountingGroup findAccountingGroup(Accounting accounting, String groupId) {
        return accounting.getExpenses().stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Group[" + groupId + "]"));
    }

    private AccountingCategory findAccountingCategory(Accounting accounting, String groupId, String categoryId) {
        AccountingGroup group = findAccountingGroup(accounting, groupId);
        return group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException("Category[" + categoryId + "]"));
    }
}
