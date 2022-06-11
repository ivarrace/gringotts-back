package com.ivarrace.gringotts.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private String id;
    private LocalDate date;
    private double amount;
    private String info;

}
