package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.AccountingGroupType;
import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingCategory;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import com.ivarrace.gringotts.repository.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecordsService {

    @Autowired
    private AccountingRepository accountingRepository;

    private Accounting findAccountingById(String id) {
        return accountingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public List<Record> findAll(String accountingId, AccountingGroupType type, String groupId, String categoryId) {
        Accounting accounting = findAccountingById(accountingId);
        List<AccountingGroup> groups = AccountingGroupType.EXPENSE.equals(type)?
                accounting.getExpenses() :
                accounting.getIncome();
        AccountingGroup group = groups.stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        return category.getRecords();
    }

    public Record findById(String accountingId, AccountingGroupType type, String groupId, String categoryId, String recordId) {
        Accounting accounting = findAccountingById(accountingId);
        List<AccountingGroup> groups = AccountingGroupType.EXPENSE.equals(type)?
                accounting.getExpenses() :
                accounting.getIncome();
        AccountingGroup group = groups.stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        return category.getRecords().stream()
                .filter((item -> recordId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(recordId));
    }

    public Accounting create(String accountingId, AccountingGroupType type, String groupId, String categoryId,
                             Record record) {
        Accounting accounting = findAccountingById(accountingId);
        List<AccountingGroup> groups = AccountingGroupType.EXPENSE.equals(type)?
                accounting.getExpenses() :
                accounting.getIncome();
        AccountingGroup group = groups.stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        record.setId(UUID.randomUUID().toString());
        category.getRecords().add(record);
        return accountingRepository.save(accounting);
    }

    public Accounting delete(String accountingId, AccountingGroupType type, String groupId, String categoryId, String recordId) {
        Accounting accounting = findAccountingById(accountingId);
        List<AccountingGroup> groups = AccountingGroupType.EXPENSE.equals(type)?
                accounting.getExpenses() :
                accounting.getIncome();
        AccountingGroup group = groups.stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        category.getRecords().removeIf(item -> recordId.equals(item.getId()));
        return accountingRepository.save(accounting);
    }

    public Accounting modify(String accountingId, AccountingGroupType type, String groupId, String categoryId, String recordId, Record newRecord) {
        Accounting accounting = findAccountingById(accountingId);
        List<AccountingGroup> groups = AccountingGroupType.EXPENSE.equals(type)?
                accounting.getExpenses() :
                accounting.getIncome();
        AccountingGroup group = groups.stream()
                .filter((item -> groupId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(accountingId));
        AccountingCategory category = group.getCategories().stream()
                .filter(item -> categoryId.equals(item.getId()))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(groupId));
        Record actual = category.getRecords().stream()
                .filter((item -> recordId.equals(item.getId())))
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(recordId));
        actual.setCantidad(newRecord.getCantidad());
        actual.setFecha(newRecord.getFecha());
        actual.setInfo(newRecord.getInfo());
        return accountingRepository.save(accounting);
    }
}
