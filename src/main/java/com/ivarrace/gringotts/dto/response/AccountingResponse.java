package com.ivarrace.gringotts.dto.response;

import java.util.Date;

public class AccountingResponse {

    private String id;
    private Date createdDate;
    private Date lastModified;
    private String name;
    private ReportResponse expenses;
    private ReportResponse income;
    private RecordsSummary savings;

    public AccountingResponse() {
        this.savings = new RecordsSummary();
    }

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

    public ReportResponse getExpenses() {
        return expenses;
    }

    public void setExpenses(ReportResponse expenses) {
        this.expenses = expenses;
    }

    public ReportResponse getIncome() {
        return income;
    }

    public void setIncome(ReportResponse income) {
        this.income = income;
    }

    public RecordsSummary getSavings() {
        return savings;
    }

    public void setSavings(RecordsSummary savings) {
        this.savings = savings;
    }
}
