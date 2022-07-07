package com.ivarrace.gringotts.application.controller;

import com.ivarrace.gringotts.application.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.dto.request.RecordRequest;
import com.ivarrace.gringotts.application.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.dto.response.GroupResponse;
import com.ivarrace.gringotts.application.dto.response.AccountingResponse;
import com.ivarrace.gringotts.application.dto.response.RecordResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;
import com.ivarrace.gringotts.domain.service.CategoryService;
import com.ivarrace.gringotts.domain.service.GroupService;
import com.ivarrace.gringotts.domain.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}/expenses")
public class ExpensesController {

    private final GroupService groupService;
    private final CategoryService categoryService;
    private final RecordService recordService;

    public ExpensesController(GroupService groupService,
                              CategoryService categoryService,
                              RecordService recordService) {
        this.groupService = groupService;
        this.categoryService = categoryService;
        this.recordService = recordService;
    }

    @GetMapping("/")
    public List<GroupResponse> findExpenses(@PathVariable String accountingId) {
        return groupService.findAllByType(accountingId, GroupType.EXPENSES);
    }

    @GetMapping("/{groupId}")
    public GroupResponse findExpenseById(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupService.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public AccountingResponse createExpense(@PathVariable String accountingId,
                                            @RequestBody GroupRequest request) {
        return groupService.create(accountingId, GroupType.EXPENSES, request);
    }

    @DeleteMapping("/{groupId}")
    public AccountingResponse deleteExpense(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupService.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public AccountingResponse modifyExpense(@PathVariable String accountingId, @PathVariable String groupId,
                                            @RequestBody GroupRequest request) {
        return groupService.modify(accountingId, groupId, request);
    }

    @GetMapping("/{groupId}/categories/")
    public List<CategoryResponse> findExpenseCategories(@PathVariable String accountingId,
                                                        @PathVariable String groupId) {
        return categoryService.findAllCategories(accountingId, groupId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}")
    public CategoryResponse findExpenseCategoryById(@PathVariable String accountingId,
                                                    @PathVariable String groupId,
                                                    @PathVariable String categoryId) {
        return categoryService.findCategoryById(accountingId, groupId, categoryId);
    }

    @PostMapping("/{groupId}/categories/")
    public AccountingResponse createExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @RequestBody CategoryRequest request) {
        return categoryService.createCategory(accountingId, groupId, request);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse deleteExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @PathVariable String categoryId) {
        return categoryService.deleteCategoryById(accountingId, groupId, categoryId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse modifyExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @PathVariable String categoryId,
                                                    @RequestBody CategoryRequest request) {
        return categoryService.modifyCategory(accountingId, groupId, categoryId, request);
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records")
    public List<RecordResponse> findAllExpenseRecords(@PathVariable String accountingId,
                                                      @PathVariable String groupId,
                                                      @PathVariable String categoryId){
        return recordService.findAll(accountingId, groupId, categoryId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public RecordResponse findExpenseRecordById(@PathVariable String accountingId,
                                  @PathVariable String groupId,
                                  @PathVariable String categoryId,
                                  @PathVariable String recordId){
        return recordService.findById(accountingId, groupId, categoryId, recordId);
    }

    @PostMapping("/{groupId}/categories/{categoryId}/records")
    public AccountingResponse createExpenseRecord(@PathVariable String accountingId,
                                    @PathVariable String groupId,
                                    @PathVariable String categoryId,
                                    @RequestBody RecordRequest request) {
        return recordService.create(accountingId, groupId, categoryId, request);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse deleteExpenseRecordById(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId,
                                        @PathVariable String recordId){
        return recordService.delete(accountingId, groupId, categoryId, recordId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse modifyExpenseRecordById(@PathVariable String accountingId,
                                        @PathVariable String groupId,
                                        @PathVariable String categoryId,
                                        @PathVariable String recordId,
                                        @RequestBody RecordRequest request){
        return recordService.modify(accountingId, groupId, categoryId, recordId, request);
    }
}
