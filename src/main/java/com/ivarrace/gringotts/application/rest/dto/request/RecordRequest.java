package com.ivarrace.gringotts.application.dto.request;

import java.time.LocalDate;

public class RecordRequest {

    private LocalDate date;
    private double amount;
    private String info;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
