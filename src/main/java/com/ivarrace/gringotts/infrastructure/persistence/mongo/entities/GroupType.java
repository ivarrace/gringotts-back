package com.ivarrace.gringotts.infrastructure.persistence.mongo.entities;

import com.ivarrace.gringotts.domain.exception.IllegalGroupTypeException;

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
