package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingCategory;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExpensesService {

    @Autowired
    private AccountingRepository accountingRepository;

    public List<AccountingGroup> findAll(String accountingId) {
        return findAccountingById(accountingId).getExpenses();
    }

    public AccountingGroup findById(String accountingId, String groupId) {
        return findAccountingById(accountingId).getExpenses()
                .stream()
                .filter((group -> groupId.equals(group.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
    }

    private Accounting findAccountingById(String id) {
        return accountingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Accounting create(String accountingId, AccountingGroup group) {
        Accounting actual = findAccountingById(accountingId);
        group.setId(UUID.randomUUID().toString());
        group.setCreatedDate(new Date());
        group.setCategories(Collections.emptyList());
        actual.getExpenses().add(group);
        return accountingRepository.save(actual);
    }

    public Accounting deleteById(String accountingId, String groupId) {
        Accounting actual = findAccountingById(accountingId);
        actual.getExpenses().removeIf(group -> groupId.equals(group.getId()));
        return accountingRepository.save(actual);
    }

    public Accounting modify(String accountingId, String groupId, AccountingGroup newGroup) {
        Accounting actual = findAccountingById(accountingId);
        AccountingGroup actualGroup = actual.getExpenses().stream()
                .filter(group -> groupId.equals(group.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        actualGroup.setName(newGroup.getName());
        return accountingRepository.save(actual);
    }

    public List<AccountingCategory> findAllCategories(String accountingId, String groupId) {
        return findById(accountingId, groupId).getCategories();
    }

    public AccountingCategory findCategoryById(String accountingId, String groupId, String categoryId) {
        return findById(accountingId, groupId).getCategories()
                .stream()
                .filter((category -> categoryId.equals(category.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
    }

    public Accounting createCategory(String accountingId, String groupId, AccountingCategory category) {
        Accounting accounting = findAccountingById(accountingId);
        AccountingGroup group = accounting.getExpenses().stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        category.setId(UUID.randomUUID().toString());
        category.setCreatedDate(new Date());
        category.setRecords(Collections.emptyList());
        group.getCategories().add(category);
        return accountingRepository.save(accounting);
    }

    public Accounting deleteCategoryById(String accountingId, String groupId, String categoryId) {
        Accounting accounting = findAccountingById(accountingId);
        AccountingGroup group = accounting.getExpenses().stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        group.getCategories().removeIf(item -> categoryId.equals(item.getId()));
        return accountingRepository.save(accounting);
    }

    public Accounting modifyCategory(String accountingId, String groupId, String categoryId, AccountingCategory newCategory) {
        Accounting accounting = findAccountingById(accountingId);
        AccountingGroup group = accounting.getExpenses().stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        category.setName(newCategory.getName());
        return accountingRepository.save(accounting);
    }

}
