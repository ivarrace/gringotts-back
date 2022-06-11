package com.ivarrace.gringotts.application.dto.response;

import com.ivarrace.gringotts.infrastructure.persistence.mongo.entities.GroupType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class GroupResponse {

    private String id;
    private LocalDateTime createdDate;
    private String name;
    private List<CategoryResponse> categories;
    private RecordsSummary recordsSummary;
    private GroupType type;

    public GroupResponse() {
        this.categories = Collections.emptyList();
        this.recordsSummary = new RecordsSummary();
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

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecordsSummary getAnnualTotals() {
        return recordsSummary;
    }

    public void setAnnualTotals(RecordsSummary recordsSummary) {
        this.recordsSummary = recordsSummary;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }
}
