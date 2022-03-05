package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingCategory;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import com.ivarrace.gringotts.service.AccountingService;
import com.ivarrace.gringotts.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}/income")
public class IncomeController {

    @Autowired
    private AccountingService accountingService;

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/")
    public List<AccountingGroup> findIncome(@PathVariable String accountingId) {
        return incomeService.findAll(accountingId);
    }

    @GetMapping("/{groupId}")
    public AccountingGroup findIncomeById(@PathVariable String accountingId, @PathVariable String groupId) {
        return incomeService.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public Accounting create(@PathVariable String accountingId, @RequestBody AccountingGroup group) {
        return incomeService.create(accountingId, group);
    }

    @DeleteMapping("/{groupId}")
    public Accounting deleteIncome(@PathVariable String accountingId, @PathVariable String groupId) {
        return incomeService.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public Accounting put(@PathVariable String accountingId, @PathVariable String groupId,
                          @RequestBody AccountingGroup group) {
        return incomeService.modify(accountingId, groupId, group);
    }

    @GetMapping("/{groupId}/categories/")
    public List<AccountingCategory> findCategories(@PathVariable String accountingId, @PathVariable String groupId) {
        return incomeService.findAllCategories(accountingId, groupId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}")
    public AccountingCategory findCategoryById(
            @PathVariable String accountingId,
            @PathVariable String groupId,
            @PathVariable String categoryId) {
        return incomeService.findCategoryById(accountingId, groupId, categoryId);
    }

    @PostMapping("/{groupId}/categories/")
    public Accounting createCategory(@PathVariable String accountingId,
                                     @PathVariable String groupId,
                                     @RequestBody AccountingCategory category) {
        return incomeService.createCategory(accountingId, groupId, category);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}")
    public Accounting deleteIncome(@PathVariable String accountingId,
                                    @PathVariable String groupId,
                                    @PathVariable String categoryId) {
        return incomeService.deleteCategoryById(accountingId, groupId, categoryId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}")
    public Accounting putCategory(@PathVariable String accountingId,
                                  @PathVariable String groupId,
                                  @PathVariable String categoryId,
                                  @RequestBody AccountingCategory category) {
        return incomeService.modifyCategory(accountingId, groupId, categoryId, category);
    }
}
