package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.dto.request.AccountingRequest;
import com.ivarrace.gringotts.dto.response.AccountingResponse;
import com.ivarrace.gringotts.dto.response.GroupResponse;
import com.ivarrace.gringotts.repository.model.Accounting;
import com.ivarrace.gringotts.repository.model.Group;
import com.ivarrace.gringotts.repository.model.GroupType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountingMapperTest {

    @Mock
    private GroupMapper groupMapper;

    private AccountingMapper accountingMapper;

    @BeforeEach
    void setUp() {
        accountingMapper = new AccountingMapper(groupMapper);
    }

    @Test
    void toDto_empty_groups() {
        Accounting entity = newTestAccounting();
        AccountingResponse result = accountingMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(entity.getLastModified(), result.getLastModified());
        assertTrue(result.getExpenses().isPresent());
        assertEquals(0, result.getExpenses().get().getGroups().size());
        assertTrue(result.getIncome().isPresent());
        assertEquals(0, result.getIncome().get().getGroups().size());
        verify(groupMapper, times(0)).toDto(any());
    }

    @Test
    void toDto() {
        Accounting entity = newTestAccounting();
        Group expense = newTestGroup(GroupType.EXPENSES);
        Group income = newTestGroup(GroupType.INCOME);
        entity.setGroups(Arrays.asList(expense, income));
        GroupResponse expenseDto = new GroupResponse();
        when(groupMapper.toDto(expense)).thenReturn(expenseDto);
        GroupResponse incomeDto = new GroupResponse();
        when(groupMapper.toDto(income)).thenReturn(incomeDto);
        AccountingResponse result = accountingMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(entity.getLastModified(), result.getLastModified());
        assertTrue(result.getExpenses().isPresent());
        assertEquals(1, result.getExpenses().get().getGroups().size());
        assertTrue(result.getIncome().isPresent());
        assertEquals(1, result.getIncome().get().getGroups().size());
        verify(groupMapper, times(2)).toDto(any());
    }

    @Test
    void toDtoList() {
        Accounting entityA = newTestAccounting();
        List<AccountingResponse> result = accountingMapper.toDtoList(Collections.singletonList(entityA));
        assertEquals(1, result.size());
    }

    @Test
    void toDtoList_null() {
        List<AccountingResponse> result = accountingMapper.toDtoList(null);
        assertEquals(0, result.size());
    }

    @Test
    void toDtoList_empty() {
        List<AccountingResponse> result = accountingMapper.toDtoList(Collections.emptyList());
        assertEquals(0, result.size());
    }

    @Test
    void toNewEntity() {
        AccountingRequest request = new AccountingRequest();
        Accounting result = accountingMapper.toNewEntity(request);
        assertEquals(request.getName(), result.getName());
        assertEquals(0, result.getGroups().size());
        assertNull(result.getId());
        assertNull(result.getCreatedDate());
        assertNull(result.getLastModified());
    }

    private Accounting newTestAccounting() {
        Accounting entity = new Accounting();
        entity.setId("test-id");
        entity.setName("test-accounting");
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastModified(LocalDateTime.now());
        entity.setGroups(Collections.emptyList());
        return entity;
    }

    private Group newTestGroup(GroupType type) {
        Group group = new Group();
        group.setId("test-" + type.name());
        group.setName("test-" + type.name());
        group.setCreatedDate(LocalDateTime.now());
        group.setType(type);
        group.setCategories(Collections.emptyList());
        return group;
    }
}