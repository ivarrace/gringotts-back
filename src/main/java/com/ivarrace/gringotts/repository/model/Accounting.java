package com.ivarrace.gringotts.repository.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "accounting")
public class Accounting {

    @Id
    private String id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModified;
    private String name;
    private List<AccountingGroup> expenses;
    private List<AccountingGroup> income;


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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountingGroup> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<AccountingGroup> expenses) {
        this.expenses = expenses;
    }

    public List<AccountingGroup> getIncome() {
        return income;
    }

    public void setIncome(List<AccountingGroup> income) {
        this.income = income;
    }
}