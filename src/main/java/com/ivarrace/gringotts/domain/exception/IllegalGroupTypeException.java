package com.ivarrace.gringotts.domain.exception;

public class IllegalGroupTypeException extends RuntimeException {

    public IllegalGroupTypeException(String type) {
        super("Accounting group type "+type+" invalid.");
    }

}
