package com.ivarrace.gringotts.repository.model;

import com.ivarrace.gringotts.exception.IllegalGroupTypeException;

public enum GroupType {
    EXPENSES,
    INCOME;

    public static GroupType get(String name){
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new IllegalGroupTypeException(name);
        }
    }
}
