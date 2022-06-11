package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.application.dto.mapper.CategoryMapper;
import com.ivarrace.gringotts.application.dto.mapper.RecordMapper;
import com.ivarrace.gringotts.application.dto.request.CategoryRequest;
import com.ivarrace.gringotts.application.dto.response.CategoryResponse;
import com.ivarrace.gringotts.application.dto.response.RecordResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Category;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryMapperTest {

    @Mock
    RecordMapper recordMapper;

    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapper(recordMapper);
    }

    @Test
    void toDto_empty_records() {
        Category entity = newTestCategory();
        CategoryResponse result = categoryMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(0, result.getRecords().size());
        verify(recordMapper, times(0)).toDto(any());
    }

    @Test
    void toDto() {
        Category entity = newTestCategory();
        Record record = newTestRecord();
        entity.setRecords(Collections.singletonList(record));
        when(recordMapper.toDtoList(any())).thenReturn(Collections.singletonList(new RecordResponse()));
        CategoryResponse result = categoryMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getCreatedDate(), result.getCreatedDate());
        assertEquals(1, result.getRecords().size());
        verify(recordMapper, times(1)).toDtoList(any());
    }

    @Test
    void toDtoList() {
        Category entity = newTestCategory();
        List<CategoryResponse> result = categoryMapper.toDtoList(Collections.singletonList(entity));
        assertEquals(1, result.size());
    }

    @Test
    void toDtoList_null() {
        List<CategoryResponse> result = categoryMapper.toDtoList(null);
        assertEquals(0, result.size());
    }

    @Test
    void toDtoList_empty() {
        List<CategoryResponse> result = categoryMapper.toDtoList(Collections.emptyList());
        assertEquals(0, result.size());
    }

    @Test
    void toNewEntity() {
        CategoryRequest request = new CategoryRequest();
        Category result = categoryMapper.toNewEntity(request);
        assertEquals(request.getName(), result.getName());
        assertEquals(0, result.getRecords().size());
        assertNotNull(result.getId());
        assertNotNull(result.getCreatedDate());
    }

    private Category newTestCategory() {
        Category category = new Category();
        category.setId("test-category");
        category.setName("test-category");
        category.setCreatedDate(LocalDateTime.now());
        category.setRecords(Collections.emptyList());
        return category;
    }

    private Record newTestRecord() {
        Record record = new Record();
        record.setId("test-record");
        record.setDate(LocalDate.now());
        record.setAmount(42.00);
        record.setInfo("test-info");
        return record;
    }

}