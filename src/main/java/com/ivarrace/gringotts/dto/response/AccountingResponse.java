package com.ivarrace.gringotts.dto.response;

import com.ivarrace.gringotts.repository.model.AccountingGroup;

import java.util.Date;
import java.util.List;

public class AccountingResponse {

    private String id;
    private Date createdDate;
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
