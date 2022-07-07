package com.ivarrace.gringotts.application.rest.dto.response;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CategoryResponse {
    private String id;
    private LocalDateTime createdDate;
    private String name;
    private List<RecordResponse> records;
    private RecordsSummary recordsSummary;

    public CategoryResponse() {
        this.records = Collections.emptyList();
        this.recordsSummary = new RecordsSummary();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<RecordResponse> records) {
        this.records = records;
    }

    public RecordsSummary getAnnualTotals() {
        return recordsSummary;
    }

    public void setAnnualTotals(RecordsSummary recordsSummary) {
        this.recordsSummary = recordsSummary;
    }
}
