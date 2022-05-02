package com.ivarrace.gringotts.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String username) {
        super("User " + username + " already registered.");
    }

}
