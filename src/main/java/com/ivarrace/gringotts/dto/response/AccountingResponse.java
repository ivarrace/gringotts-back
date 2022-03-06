package com.ivarrace.gringotts.dto.response;

import java.util.Date;
import java.util.List;

public class AccountingResponse {

    private String id;
    private Date createdDate;
    private Date lastModified;
    private String name;
    private List<GroupResponse> expenses;
    private List<GroupResponse> income;

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

    public List<GroupResponse> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<GroupResponse> expenses) {
        this.expenses = expenses;
    }

    public List<GroupResponse> getIncome() {
        return income;
    }

    public void setIncome(List<GroupResponse> income) {
        this.income = income;
    }
}
