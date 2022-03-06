package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.dto.request.CategoryRequest;
import com.ivarrace.gringotts.dto.request.GroupRequest;
import com.ivarrace.gringotts.dto.request.RecordRequest;
import com.ivarrace.gringotts.dto.response.CategoryResponse;
import com.ivarrace.gringotts.dto.response.GroupResponse;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.dto.response.RecordResponse;
import com.ivarrace.gringotts.repository.model.GroupType;
import com.ivarrace.gringotts.service.CategoryService;
import com.ivarrace.gringotts.service.GroupService;
import com.ivarrace.gringotts.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/accounting/{accountingId}/income")
public class IncomeController {

    private final GroupService groupService;
    private final CategoryService categoryService;
    private final RecordService recordService;

    public IncomeController(GroupService groupService,
                            CategoryService categoryService,
                            RecordService recordService) {
        this.groupService = groupService;
        this.categoryService = categoryService;
        this.recordService = recordService;
    }

    @GetMapping("/")
    public List<GroupResponse> findIncome(@PathVariable String accountingId) {
        return groupService.findAllByType(accountingId, GroupType.INCOME);
    }

    @GetMapping("/{groupId}")
    public GroupResponse findIncomeById(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupService.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public AccountingResponse createIncome(@PathVariable String accountingId,
                                           @RequestBody GroupRequest request) {
        return groupService.create(accountingId, GroupType.INCOME, request);
    }

    @DeleteMapping("/{groupId}")
    public AccountingResponse deleteIncome(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupService.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public AccountingResponse modifyIncome(@PathVariable String accountingId, @PathVariable String groupId,
                                           @RequestBody GroupRequest request) {
        return groupService.modify(accountingId, groupId, request);
    }

    @GetMapping("/{groupId}/categories/")
    public List<CategoryResponse> findIncomeCategories(@PathVariable String accountingId,
                                                       @PathVariable String groupId) {
        return categoryService.findAllCategories(accountingId, groupId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}")
    public CategoryResponse findIncomeCategoryById(
            @PathVariable String accountingId,
            @PathVariable String groupId,
            @PathVariable String categoryId) {
        return categoryService.findCategoryById(accountingId, groupId, categoryId);
    }

    @PostMapping("/{groupId}/categories/")
    public AccountingResponse createIncomeCategory(@PathVariable String accountingId,
                                                   @PathVariable String groupId,
                                                   @RequestBody CategoryRequest request) {
        return categoryService.createCategory(accountingId, groupId, request);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse deleteIncomeCategory(@PathVariable String accountingId,
                                                   @PathVariable String groupId,
                                                   @PathVariable String categoryId) {
        return categoryService.deleteCategoryById(accountingId, groupId, categoryId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse modifyIncomeCategory(@PathVariable String accountingId,
                                                   @PathVariable String groupId,
                                                   @PathVariable String categoryId,
                                                   @RequestBody CategoryRequest request) {
        return categoryService.modifyCategory(accountingId, groupId, categoryId, request);
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records")
    public List<RecordResponse> findAllIncomeRecords(@PathVariable String accountingId,
                                                     @PathVariable String groupId,
                                                     @PathVariable String categoryId) {
        return recordService.findAll(accountingId, groupId, categoryId);
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public RecordResponse findIncomeRecordById(@PathVariable String accountingId,
                                 @PathVariable String groupId,
                                 @PathVariable String categoryId,
                                 @PathVariable String recordId){
        return recordService.findById(accountingId, groupId, categoryId, recordId);
    }

    @PostMapping("/{groupId}/categories/{categoryId}/records")
    public AccountingResponse createIncomeRecord(@PathVariable String accountingId,
                                           @PathVariable String groupId,
                                           @PathVariable String categoryId,
                                           @RequestBody RecordRequest request) {
        return recordService.create(accountingId, groupId, categoryId, request);
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse deleteIncomeRecordById(@PathVariable String accountingId,
                                               @PathVariable String groupId,
                                               @PathVariable String categoryId,
                                               @PathVariable String recordId){
        return recordService.delete(accountingId, groupId, categoryId, recordId);
    }

    @PutMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse modifyIncomeRecordById(@PathVariable String accountingId,
                                               @PathVariable String groupId,
                                               @PathVariable String categoryId,
                                               @PathVariable String recordId,
                                               @RequestBody RecordRequest request){
        return recordService.modify(accountingId, groupId, categoryId, recordId, request);
    }
}
