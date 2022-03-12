package com.ivarrace.gringotts.exception;

public class ObjectAlreadyExistsException extends RuntimeException {

    public ObjectAlreadyExistsException(String id) {
        super("Object " + id + " already exists.");
    }

}
