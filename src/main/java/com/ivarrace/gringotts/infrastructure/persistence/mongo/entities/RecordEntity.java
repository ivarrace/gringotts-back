package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordEntity {

    private String id;
    private LocalDate date;
    private double amount;
    private String info;

}