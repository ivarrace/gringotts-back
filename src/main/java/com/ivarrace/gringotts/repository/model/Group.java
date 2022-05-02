package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

public class Group {

    private String id;
    @CreatedDate
    private LocalDateTime createdDate;
    private String name;
    private GroupType type;
    private List<Category> categories;

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
