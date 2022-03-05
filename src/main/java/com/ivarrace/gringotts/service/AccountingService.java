package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountingService {

    private final AccountingRepository accountingRepository;
    private final AccountingMapper accountingMapper;

    public AccountingService(AccountingRepository accountingRepository,
                             AccountingMapper accountingMapper){
        this.accountingRepository=accountingRepository;
        this.accountingMapper=accountingMapper;
    }

    public List<AccountingResponse> findAll() {
        return accountingMapper.toDto(accountingRepository.findAll());
    }

    public AccountingResponse findById(String id) {
        return accountingMapper.toDto(findAccountingEntity(id));
    }

    public AccountingResponse create(AccountingRequest accounting) {
        return accountingMapper.toDto(accountingRepository.save(accountingMapper.toNewEntity(accounting)));
    }

    public void deleteById(String id) {
        accountingRepository.delete(findAccountingEntity(id));
    }

    public AccountingResponse modify(String id, AccountingRequest accounting) {
        Accounting actual = findAccountingEntity(id);
        actual.setName(accounting.getName());
        return accountingMapper.toDto(accountingRepository.save(actual));
    }

    private Accounting findAccountingEntity(String accountingId){
        return accountingRepository.findById(accountingId).orElseThrow(() -> new ObjectNotFoundException("Accounting["+accountingId+"]"));
    }

}
