package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

public class AccountingCategory {

    private String id;
    @CreatedDate
    private Date createdDate;
    private String name;
    private List<Record> records;

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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
