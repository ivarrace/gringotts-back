package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.application.dto.mapper.CategoryMapper;
import com.ivarrace.gringotts.application.dto.mapper.GroupMapper;
import com.ivarrace.gringotts.application.dto.request.GroupRequest;
import com.ivarrace.gringotts.application.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.dto.response.GroupResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Category;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Group;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupMapperTest {

    @Mock
    CategoryMapper categoryMapper;

    private GroupMapper groupMapper;

    @BeforeEach
    void setUp() {
        groupMapper = new GroupMapper(categoryMapper);
    }

    @Test
    void toDto_empty_categories() {
        Group entity = newTestGroup();
        GroupResponse result = groupMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(0, result.getCategories().size());
        verify(categoryMapper, times(0)).toDto(any());
    }

    @Test
    void toDto() {
        Group entity = newTestGroup();
        Category expense = newTestCategory();
        entity.setCategories(Collections.singletonList(expense));
        when(categoryMapper.toDtoList(any())).thenReturn(Collections.singletonList(new CategoryResponse()));
        GroupResponse result = groupMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(1, result.getCategories().size());
        verify(categoryMapper, times(1)).toDtoList(any());
    }

    @Test
    void toDtoList() {
        Group entity = newTestGroup();
        List<GroupResponse> result = groupMapper.toDtoList(Collections.singletonList(entity));
        assertEquals(1, result.size());
    }

    @Test
    void toDtoList_null() {
        List<GroupResponse> result = groupMapper.toDtoList(null);
        assertEquals(0, result.size());
    }

    @Test
    void toDtoList_empty() {
        List<GroupResponse> result = groupMapper.toDtoList(Collections.emptyList());
        assertEquals(0, result.size());
    }

    @Test
    void toNewEntity() {
        GroupRequest request = new GroupRequest();
        Group result = groupMapper.toNewEntity(GroupType.EXPENSES, request);
        assertEquals(request.getName(), result.getName());
        assertEquals(0, result.getCategories().size());
        assertEquals(GroupType.EXPENSES, result.getType());
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedDate());
    }

    private Group newTestGroup() {
        Group group = new Group();
        group.setId("test-expense");
        group.setName("test-expense");
        group.setCreatedDate(LocalDateTime.now());
        group.setType(GroupType.EXPENSES);
        group.setCategories(Collections.emptyList());
        return group;
    }

    private Category newTestCategory() {
        Category category = new Category();
        category.setId("test-category");
        category.setName("test-category");
        category.setCreatedDate(LocalDateTime.now());
        category.setRecords(Collections.emptyList());
        return category;
    }
}