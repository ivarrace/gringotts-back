package com.ivarrace.gringotts.controller;

import com.ivarrace.gringotts.dto.ErrorResponse;
import com.ivarrace.gringotts.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(ObjectNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
