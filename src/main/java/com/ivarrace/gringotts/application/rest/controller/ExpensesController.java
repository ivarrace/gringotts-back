package com.ivarrace.gringotts.application.rest.controller;

import com.ivarrace.gringotts.application.facade.GroupFacade;
import com.ivarrace.gringotts.application.rest.dto.mapper.AccountingMapper;
import com.ivarrace.gringotts.application.rest.dto.mapper.CategoryMapper;
import com.ivarrace.gringotts.application.rest.dto.mapper.GroupMapper;
import com.ivarrace.gringotts.application.rest.dto.mapper.RecordMapper;
import com.ivarrace.gringotts.application.rest.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.rest.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.rest.dto.request.RecordRequest;
import com.ivarrace.gringotts.application.rest.dto.response.AccountingResponse;
import com.ivarrace.gringotts.application.rest.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.rest.dto.response.GroupResponse;
import com.ivarrace.gringotts.application.rest.dto.response.RecordResponse;
import com.ivarrace.gringotts.domain.model.GroupType;
import com.ivarrace.gringotts.domain.service.CategoryService;
import com.ivarrace.gringotts.domain.service.GroupService;
import com.ivarrace.gringotts.domain.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/accounting/{accountingId}/expenses")
public class ExpensesController {

    private final CategoryService categoryService;
    private final RecordService recordService;
    private final GroupFacade groupFacade;

    public ExpensesController(CategoryService categoryService,
                              RecordService recordService,
                              GroupFacade groupFacade) {
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.groupFacade = groupFacade;
    }

    @GetMapping("/")
    public List<GroupResponse> findExpenses(@PathVariable String accountingId) {
        return groupFacade.findAll(accountingId, GroupType.EXPENSES);
    }

    @GetMapping("/{groupId}")
    public GroupResponse findExpenseById(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupFacade.findById(accountingId, groupId);
    }

    @PostMapping("/")
    public AccountingResponse createExpense(@PathVariable String accountingId,
                                            @RequestBody GroupRequest request) {
        return groupFacade.create(accountingId, GroupType.EXPENSES, request);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteExpense(@PathVariable String accountingId, @PathVariable String groupId) {
        return groupFacade.deleteById(accountingId, groupId);
    }

    @PutMapping("/{groupId}")
    public AccountingResponse modifyExpense(@PathVariable String accountingId, @PathVariable String groupId,
                                            @RequestBody GroupRequest request) {
        return groupFacade.modify(accountingId, groupId, request);
    }

    @GetMapping("/{groupId}/categories/")
    public List<CategoryResponse> findExpenseCategories(@PathVariable String accountingId,
                                                        @PathVariable String groupId) {
        return categoryService.findAllCategories(accountingId, groupId).stream().map(CategoryMapper::dtoToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{groupId}/categories/{categoryId}")
    public CategoryResponse findExpenseCategoryById(@PathVariable String accountingId,
                                                    @PathVariable String groupId,
                                                    @PathVariable String categoryId) {
        return CategoryMapper.dtoToResponse(categoryService.findCategoryById(accountingId, groupId, categoryId));
    }

    @PostMapping("/{groupId}/categories/")
    public AccountingResponse createExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @RequestBody CategoryRequest request) {
        return AccountingMapper.dtoToResponse(categoryService.createCategory(accountingId, groupId, CategoryMapper.requestToDto(request)));
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse deleteExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @PathVariable String categoryId) {
        return AccountingMapper.dtoToResponse(categoryService.deleteCategoryById(accountingId, groupId, categoryId));
    }

    @PutMapping("/{groupId}/categories/{categoryId}")
    public AccountingResponse modifyExpenseCategory(@PathVariable String accountingId, @PathVariable String groupId,
                                                    @PathVariable String categoryId,
                                                    @RequestBody CategoryRequest request) {
        return AccountingMapper.dtoToResponse(categoryService.modifyCategory(accountingId, groupId, categoryId, CategoryMapper.requestToDto(request)));
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records")
    public List<RecordResponse> findAllExpenseRecords(@PathVariable String accountingId,
                                                      @PathVariable String groupId,
                                                      @PathVariable String categoryId) {
        return recordService.findAll(accountingId, groupId, categoryId).stream().map(RecordMapper::dtoToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public RecordResponse findExpenseRecordById(@PathVariable String accountingId,
                                                @PathVariable String groupId,
                                                @PathVariable String categoryId,
                                                @PathVariable String recordId) {
        return RecordMapper.dtoToResponse(recordService.findById(accountingId, groupId, categoryId, recordId));
    }

    @PostMapping("/{groupId}/categories/{categoryId}/records")
    public AccountingResponse createExpenseRecord(@PathVariable String accountingId,
                                                  @PathVariable String groupId,
                                                  @PathVariable String categoryId,
                                                  @RequestBody RecordRequest request) {
        return AccountingMapper.dtoToResponse(recordService.create(accountingId, groupId, categoryId, RecordMapper.requestToDto(request)));
    }

    @DeleteMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse deleteExpenseRecordById(@PathVariable String accountingId,
                                                      @PathVariable String groupId,
                                                      @PathVariable String categoryId,
                                                      @PathVariable String recordId) {
        return AccountingMapper.dtoToResponse(recordService.delete(accountingId, groupId, categoryId, recordId));
    }

    @PutMapping("/{groupId}/categories/{categoryId}/records/{recordId}")
    public AccountingResponse modifyExpenseRecordById(@PathVariable String accountingId,
                                                      @PathVariable String groupId,
                                                      @PathVariable String categoryId,
                                                      @PathVariable String recordId,
                                                      @RequestBody RecordRequest request) {
        return AccountingMapper.dtoToResponse(recordService.modify(accountingId, groupId, categoryId, recordId, RecordMapper.requestToDto(request)));
    }
}
