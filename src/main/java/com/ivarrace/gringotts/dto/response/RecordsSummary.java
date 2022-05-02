package com.ivarrace.gringotts.dto.response;

import com.ivarrace.gringotts.dto.DtoUtils;

import java.time.Month;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RecordsSummary {

    private double total;
    private double average;
    private Map<Month, Double> monthly;

    public RecordsSummary() {
        this.total = 0;
        this.average = 0;
        this.monthly = new EnumMap<>(Month.class);
        Arrays.asList(Month.values()).forEach(month -> this.monthly.put(month, 0d));
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAverage() {
        return DtoUtils.round(this.average);
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Map<Month, Double> getMonthly() {
        return monthly;
    }

}
