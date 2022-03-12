package com.ivarrace.gringotts.dto.response;

import java.time.LocalDateTime;

public class AccountingResponse {

    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
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
