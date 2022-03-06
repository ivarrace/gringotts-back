package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
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

    public AccountingResponse findById(String id) {
        return accountingMapper.toDto(accountingUtils.findAccountingEntity(id));
    }

    public AccountingResponse create(AccountingRequest accounting) {
        return accountingMapper.toDto(accountingRepository.save(accountingMapper.toNewEntity(accounting)));
    }

    public void deleteById(String id) {
        accountingRepository.delete(accountingUtils.findAccountingEntity(id));
    }

    public AccountingResponse modify(String id, AccountingRequest accounting) {
        Accounting actual = accountingUtils.findAccountingEntity(id);
        actual.setName(accounting.getName());
        return accountingMapper.toDto(accountingRepository.save(actual));
    }

}
