package com.ivarrace.gringotts.domain.service;

import com.ivarrace.gringotts.domain.model.Accounting;
import com.ivarrace.gringotts.domain.exception.ObjectAlreadyExistsException;
import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.model.AccountingRole;
import com.ivarrace.gringotts.infrastructure.persistence.AccountingPersistencePort;
import com.ivarrace.gringotts.infrastructure.security.dto.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingService {

    private final AccountingPersistencePort accountingPersistencePort;

    public AccountingService(AccountingPersistencePort accountingPersistencePort) {
        this.accountingPersistencePort = accountingPersistencePort;
    }

    public List<Accounting> findAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountingPersistencePort.findAll(user.getId());
    }

    public Accounting findByKey(String key) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountingPersistencePort.findByUserAndKey(user.getId(), key).orElseThrow(() -> new ObjectNotFoundException("Accounting" + "[" + key + "]"));
    }

    public Accounting create(Accounting accounting) {
        String ownerId = accounting.getUsers().stream()
                .filter(userAccountingRole -> AccountingRole.OWNER.equals(userAccountingRole.getRole()))
                .map(userAccountingRole -> userAccountingRole.getUserId()).findFirst()
                .get();
        if(!accountingPersistencePort.findByUserAndKey(ownerId, accounting.getKey()).isEmpty()){
            throw new ObjectAlreadyExistsException(accounting.getKey());
        }
        return accountingPersistencePort.save(accounting);
    }

    public void deleteById(String key) {
        Accounting accounting = this.findByKey(key);
        accountingPersistencePort.delete(accounting);
    }

    public void modify(String key, Accounting accounting) {
        Accounting actual = this.findByKey(key);
        String ownerId = accounting.getUsers().stream()
                .filter(userAccountingRole -> AccountingRole.OWNER.equals(userAccountingRole.getRole()))
                .map(userAccountingRole -> userAccountingRole.getUserId()).findFirst()
                .get();
        if(!accountingPersistencePort.findByUserAndKey(ownerId, accounting.getKey()).isEmpty()){
            throw new ObjectAlreadyExistsException(accounting.getKey());
        }
        accounting.setId(actual.getId());
        accounting.setCreatedDate(actual.getCreatedDate());
        accountingPersistencePort.save(accounting);
    }

}
