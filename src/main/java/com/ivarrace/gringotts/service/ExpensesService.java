package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
