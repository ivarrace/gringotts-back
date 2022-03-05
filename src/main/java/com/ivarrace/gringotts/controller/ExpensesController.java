package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.AccountingCategory;
import com.ivarrace.gringotts.repository.model.AccountingGroup;
import com.ivarrace.gringotts.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/")
    public List<AccountingGroup> findExpenses(@PathVariable String accountingId) {
        return expensesService.findAll(accountingId);
    }

    @GetMapping("/{groupId}")
    public AccountingGroup findExpenseById(@PathVariable String accountingId, @PathVariable String groupId) {
        return expensesService.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public Accounting create(@PathVariable String accountingId, @RequestBody AccountingGroup group) {
        return expensesService.create(accountingId, group);
    }

    @DeleteMapping("/{groupId}")
    public Accounting deleteExpense(@PathVariable String accountingId, @PathVariable String groupId) {
        return expensesService.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public Accounting put(@PathVariable String accountingId, @PathVariable String groupId,
                          @RequestBody AccountingGroup group) {
        return expensesService.modify(accountingId, groupId, group);
    }

    @GetMapping("/{groupId}/categories/")
    public List<AccountingCategory> findCategories(@PathVariable String accountingId, @PathVariable String groupId) {
        return expensesService.findAllCategories(accountingId, groupId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}")
    public AccountingCategory findCategoryById(
            @PathVariable String accountingId,
            @PathVariable String groupId,
            @PathVariable String categoryId) {
        return expensesService.findCategoryById(accountingId, groupId, categoryId);
    }

    @PostMapping("/{groupId}/categories/")
    public Accounting createCategory(@PathVariable String accountingId,
                                     @PathVariable String groupId,
                                     @RequestBody AccountingCategory category) {
        return expensesService.createCategory(accountingId, groupId, category);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}")
    public Accounting deleteExpense(@PathVariable String accountingId,
                                    @PathVariable String groupId,
                                    @PathVariable String categoryId) {
        return expensesService.deleteCategoryById(accountingId, groupId, categoryId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}")
    public Accounting putCategory(@PathVariable String accountingId,
                                  @PathVariable String groupId,
                                  @PathVariable String categoryId,
                                  @RequestBody AccountingCategory category) {
        return expensesService.modifyCategory(accountingId, groupId, categoryId, category);
    }
}
