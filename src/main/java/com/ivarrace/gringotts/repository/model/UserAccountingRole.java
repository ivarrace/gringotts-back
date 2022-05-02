package com.ivarrace.gringotts.repository.model;

public class UserAccountingRole {

    private String userId;
    private AccountingRole role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccountingRole getRole() {
        return role;
    }

    public void setRole(AccountingRole role) {
        this.role = role;
    }
}