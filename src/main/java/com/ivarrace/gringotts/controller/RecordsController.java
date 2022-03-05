package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.dto.AccountingGroupType;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.Record;
import com.ivarrace.gringotts.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}")
public class RecordsController {

    @Autowired
    private RecordsService recordsService;

    @GetMapping("/expenses/{groupId}/categories/{categoryId}/records")
    public List<Record> findAllExpenses(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId){
        return recordsService.findAll(accountingId, AccountingGroupType.EXPENSE, groupId, categoryId);
    }

    @GetMapping("/income/{groupId}/categories/{categoryId}/records")
    public List<Record> findAllIncome(@PathVariable String accountingId,
                                      @PathVariable String groupId,
                                      @PathVariable String categoryId){
        return recordsService.findAll(accountingId, AccountingGroupType.INCOME, groupId, categoryId);
    }

    @GetMapping("/expenses/{groupId}/categories/{categoryId}/records/{recordId}")
    public Record findExpenseById(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId,
                                        @PathVariable String recordId){
        return recordsService.findById(accountingId, AccountingGroupType.EXPENSE, groupId, categoryId, recordId);
    }

    @GetMapping("/income/{groupId}/categories/{categoryId}/records/{recordId}")
    public Record findIncomeById(@PathVariable String accountingId,
                                  @PathVariable String groupId,
                                  @PathVariable String categoryId,
                                  @PathVariable String recordId){
        return recordsService.findById(accountingId, AccountingGroupType.INCOME, groupId, categoryId, recordId);
    }

    @PostMapping("/expenses/{groupId}/categories/{categoryId}/records")
    public Accounting createExpense(@PathVariable String accountingId,
                             @PathVariable String groupId,
                             @PathVariable String categoryId,
                             @RequestBody Record record) {
        return recordsService.create(accountingId, AccountingGroupType.EXPENSE, groupId, categoryId, record);
    }

    @PostMapping("/income/{groupId}/categories/{categoryId}/records")
    public Accounting createIncome(@PathVariable String accountingId,
                             @PathVariable String groupId,
                             @PathVariable String categoryId,
                             @RequestBody Record record) {
        return recordsService.create(accountingId, AccountingGroupType.INCOME, groupId, categoryId, record);
    }

    @DeleteMapping("/expenses/{groupId}/categories/{categoryId}/records/{recordId}")
    public Accounting deleteExpenseById(@PathVariable String accountingId,
                                 @PathVariable String groupId,
                                 @PathVariable String categoryId,
                                 @PathVariable String recordId){
        return recordsService.delete(accountingId, AccountingGroupType.EXPENSE, groupId, categoryId, recordId);
    }

    @DeleteMapping("/income/{groupId}/categories/{categoryId}/records/{recordId}")
    public Accounting deleteIncomeById(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId,
                                        @PathVariable String recordId){
        return recordsService.delete(accountingId, AccountingGroupType.INCOME, groupId, categoryId, recordId);
    }

    @PutMapping("/expenses/{groupId}/categories/{categoryId}/records/{recordId}")
    public Accounting modifyExpenseById(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId,
                                        @PathVariable String recordId,
                                        @RequestBody Record record){
        return recordsService.modify(accountingId, AccountingGroupType.EXPENSE, groupId, categoryId, recordId, record);
    }

    @PutMapping("/income/{groupId}/categories/{categoryId}/records/{recordId}")
    public Accounting modifyIncomeById(@PathVariable String accountingId,
                                       @PathVariable String groupId,
                                       @PathVariable String categoryId,
                                       @PathVariable String recordId,
                                       @RequestBody Record record){
        return recordsService.modify(accountingId, AccountingGroupType.INCOME, groupId, categoryId, recordId, record);
    }
}
