package com.ivarrace.gringotts.dto.response;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GroupResponse {

    private String id;
    private Date createdDate;
    private String name;
    private List<CategoryResponse> categories;
    private RecordsSummary recordsSummary;

    public GroupResponse() {
        this.categories = Collections.emptyList();
        this.recordsSummary = new RecordsSummary();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
}
