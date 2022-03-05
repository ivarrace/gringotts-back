package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountingService {

    @Autowired
    private AccountingRepository accountingRepository;

    public List<Accounting> findAll() {
        return accountingRepository.findAll();
    }

    public Accounting findById(String id) {
        return accountingRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public Accounting create(Accounting accounting) {
        accounting.setExpenses(Collections.emptyList());
        accounting.setIncome(Collections.emptyList());
        return accountingRepository.save(accounting);
    }

    public void deleteById(String id) {
        Accounting accounting = findById(id);
        accountingRepository.delete(accounting);
    }

    public Accounting modify(String id, Accounting accounting) {
        Accounting actual = findById(id);
        actual.setName(accounting.getName());
        actual.setExpenses(accounting.getExpenses());
        return accountingRepository.save(actual);
    }

}
