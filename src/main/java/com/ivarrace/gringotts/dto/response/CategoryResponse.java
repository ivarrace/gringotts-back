package com.ivarrace.gringotts.dto.response;

import java.util.Date;
import java.util.List;

public class CategoryResponse {
    private String id;
    private Date createdDate;
    private String name;
    private List<RecordResponse> records;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<RecordResponse> getRecords() {
        return records;
    }

    public void setRecords(List<RecordResponse> records) {
        this.records = records;
    }
}
