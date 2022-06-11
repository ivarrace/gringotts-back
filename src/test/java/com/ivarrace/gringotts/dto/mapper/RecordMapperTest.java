package com.ivarrace.gringotts.dto.mapper;

import com.ivarrace.gringotts.application.dto.mapper.RecordMapper;
import com.ivarrace.gringotts.application.dto.request.RecordRequest;
import com.ivarrace.gringotts.application.dto.response.RecordResponse;
import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RecordMapperTest {

    private RecordMapper recordMapper;

    @BeforeEach
    void setUp() {
        recordMapper = new RecordMapper();
    }

    @Test
    void toDto() {
        Record entity = newTestRecord();
        RecordResponse result = recordMapper.toDto(entity);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getAmount(), result.getAmount());
        assertEquals(entity.getDate(), result.getDate());
        assertEquals(entity.getInfo(), result.getInfo());
    }

    @Test
    void toDtoList() {
        Record entity = newTestRecord();
        List<RecordResponse> result = recordMapper.toDtoList(Collections.singletonList(entity));
        assertEquals(1, result.size());
    }

    @Test
    void toDtoList_null() {
        List<RecordResponse> result = recordMapper.toDtoList(null);
        assertEquals(0, result.size());
    }

    @Test
    void toDtoList_empty() {
        List<RecordResponse> result = recordMapper.toDtoList(Collections.emptyList());
        assertEquals(0, result.size());
    }

    @Test
    void toNewEntity() {
        RecordRequest request = new RecordRequest();
        request.setAmount(42.42);
        request.setInfo("test");
        request.setDate(LocalDate.now());
        Record result = recordMapper.toNewEntity(request);
        assertEquals(request.getDate(), result.getDate());
        assertEquals(request.getInfo(), result.getInfo());
        assertEquals(request.getAmount(), result.getAmount());
        assertNotNull(result.getId());
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