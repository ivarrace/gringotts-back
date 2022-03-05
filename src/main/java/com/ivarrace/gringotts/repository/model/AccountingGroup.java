package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

public class AccountingGroup {

    private String id;
    @CreatedDate
    private Date createdDate;
    private String name;
    private List<AccountingCategory> categories;

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

    public List<AccountingCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<AccountingCategory> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
