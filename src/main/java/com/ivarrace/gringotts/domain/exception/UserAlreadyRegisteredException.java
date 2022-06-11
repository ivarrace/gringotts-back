package com.ivarrace.gringotts.domain.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String username) {
        super("User " + username + " already registered.");
    }

}
