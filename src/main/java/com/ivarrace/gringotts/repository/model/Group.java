package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

public class Group {

    private String id;
    @CreatedDate
    private Date createdDate;
    private String name;
    private GroupType type;
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }
}
