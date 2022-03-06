package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.Category;
import com.ivarrace.gringotts.repository.model.Group;
import com.ivarrace.gringotts.repository.model.Record;
import org.springframework.stereotype.Component;

@Component
public class AccountingUtils {

    private final AccountingRepository accountingRepository;

    public AccountingUtils(AccountingRepository accountingRepository) {
        this.accountingRepository = accountingRepository;
    }

    public Accounting findAccountingEntity(String accountingId) {
        return accountingRepository.findById(accountingId).orElseThrow(() -> new ObjectNotFoundException("Accounting" + "[" + accountingId + "]"));
    }

    public Group findAccountingGroup(Accounting accounting, String groupId) {
        return accounting.getGroups().stream().filter((item -> groupId.equals(item.getId()))).findFirst().orElseThrow(() -> new ObjectNotFoundException("Group[" + groupId + "]"));
    }

    public Category findAccountingCategory(Accounting accounting, String groupId, String categoryId) {
        Group group = findAccountingGroup(accounting, groupId);
        return group.getCategories().stream().filter(item -> categoryId.equals(item.getId())).findFirst().orElseThrow(() -> new ObjectNotFoundException("Category[" + categoryId + "]"));
    }

    public Record findAccountingRecord(Accounting accounting, String groupId, String categoryId, String recordId) {
        Category category = findAccountingCategory(accounting, groupId, categoryId);
        return category.getRecords().stream().filter(item -> recordId.equals(item.getId())).findFirst().orElseThrow(() -> new ObjectNotFoundException("Record[" + recordId + "]"));
    }
}
