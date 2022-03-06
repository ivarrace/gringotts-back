package com.ivarrace.gringotts.service;

import com.ivarrace.gringotts.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.dto.mapper.RecordMapper;
import com.ivarrace.gringotts.dto.request.RecordRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.dto.response.RecordResponse;
import com.ivarrace.gringotts.repository.AccountingRepository;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.Category;
import com.ivarrace.gringotts.repository.model.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final AccountingRepository accountingRepository;
    private final AccountingMapper accountingMapper;
    private final RecordMapper recordMapper;
    private final AccountingUtils accountingUtils;

    public RecordService(AccountingRepository accountingRepository, AccountingMapper accountingMapper,
                         RecordMapper recordMapper, AccountingUtils accountingUtils) {
        this.accountingRepository = accountingRepository;
        this.accountingMapper = accountingMapper;
        this.recordMapper = recordMapper;
        this.accountingUtils = accountingUtils;
    }

    public List<RecordResponse> findAll(String accountingId, String groupId, String categoryId) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        return recordMapper.toDtoList(accountingUtils.findAccountingCategory(accounting, groupId, categoryId).getRecords());
    }

    public RecordResponse findById(String accountingId, String groupId, String categoryId, String recordId) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        return recordMapper.toDto(accountingUtils.findAccountingRecord(accounting, groupId, categoryId, recordId));
    }

    public AccountingResponse create(String accountingId, String groupId, String categoryId, RecordRequest request) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        Category category = accountingUtils.findAccountingCategory(accounting, groupId, categoryId);
        category.getRecords().add(recordMapper.toNewEntity(request));
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse delete(String accountingId, String groupId, String categoryId, String recordId) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        Category category = accountingUtils.findAccountingCategory(accounting, groupId, categoryId);
        accountingUtils.findAccountingRecord(accounting, groupId, categoryId, recordId);
        category.getRecords().removeIf(item -> recordId.equals(item.getId()));
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }

    public AccountingResponse modify(String accountingId, String groupId, String categoryId
            , String recordId, RecordRequest request) {
        Accounting accounting = accountingUtils.findAccountingEntity(accountingId);
        Record actual = accountingUtils.findAccountingRecord(accounting, groupId, categoryId, recordId);
        actual.setAmount(request.getAmount());
        actual.setDate(request.getDate());
        actual.setInfo(request.getInfo());
        return accountingMapper.toDto(accountingRepository.save(accounting));
    }
}
