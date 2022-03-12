package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.DtoUtils;
import com.ivarrace.gringotts.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.exception.ObjectAlreadyExistsException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingService {

    private final AccountingRepository accountingRepository;
    private final AccountingMapper accountingMapper;
    private final AccountingUtils accountingUtils;

    public AccountingService(AccountingRepository accountingRepository, AccountingMapper accountingMapper,
                             AccountingUtils accountingUtils) {
        this.accountingRepository = accountingRepository;
        this.accountingMapper = accountingMapper;
        this.accountingUtils = accountingUtils;
    }

    public List<AccountingResponse> findAll() {
        return accountingMapper.toDtoList(accountingRepository.findAll());
    }

    public AccountingResponse findByKey(String key) {
        return accountingMapper.toDto(accountingUtils.findAccountingEntityByKey(key));
    }

    public AccountingResponse create(AccountingRequest accounting) {
        Accounting entity = accountingMapper.toNewEntity(accounting);
        if(!accountingRepository.findByKey(entity.getKey()).isEmpty()){
            throw new ObjectAlreadyExistsException(entity.getKey());
        }
        return accountingMapper.toDto(accountingRepository.save(entity));
    }

    public void deleteById(String key) {
        accountingRepository.delete(accountingUtils.findAccountingEntityByKey(key));
    }

    public void modify(String key, AccountingRequest accounting) {
        Accounting actual = accountingUtils.findAccountingEntityByKey(key);
        String newKey = DtoUtils.generateKey(accounting.getName());
        if(!accountingRepository.findByKey(newKey).isEmpty()){
            throw new ObjectAlreadyExistsException(newKey);
        }
        actual.setKey(newKey);
        actual.setName(accounting.getName());
        accountingMapper.toDto(accountingRepository.save(actual));
    }

}
