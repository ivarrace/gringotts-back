package com.ivarrace.gringotts.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Record {

    private String id;
    private LocalDate date;
    private double amount;
    private String info;

}
