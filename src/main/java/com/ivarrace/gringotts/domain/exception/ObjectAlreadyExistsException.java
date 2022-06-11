package com.ivarrace.gringotts.domain.exception;

public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String id) {
        super("Object " + id + " already exists.");
    }

}
