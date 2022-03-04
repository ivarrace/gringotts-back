package com.ivarrace.gringotts.exception;

public class ObjectNotFoundException extends RuntimeException {

    private final String id;

    public ObjectNotFoundException(String id) {
        super("Object [" + id + "] not found.");
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
