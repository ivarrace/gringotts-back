package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import com.ivarrace.gringotts.service.AccountingService;
import com.ivarrace.gringotts.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}/expenses")
public class ExpensesController {

    @Autowired
    private AccountingService accountingService;

    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/")
    public List<AccountingGroup> findExpenses(@PathVariable String accountingId){
        return expensesService.findAll(accountingId);
    }

    @GetMapping("/{groupId}")
    public AccountingGroup findExpenseById(@PathVariable String accountingId, @PathVariable String groupId){
        return expensesService.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public Accounting create(@PathVariable String accountingId, @RequestBody AccountingGroup group){
        return expensesService.create(accountingId, group);
    }

    @DeleteMapping("/{groupId}")
    public Accounting deleteExpense(@PathVariable String accountingId, @PathVariable String groupId){
        return expensesService.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public Accounting put(@PathVariable String accountingId, @PathVariable String groupId, @RequestBody AccountingGroup group){
        return expensesService.modify(accountingId, groupId, group);
    }
}
