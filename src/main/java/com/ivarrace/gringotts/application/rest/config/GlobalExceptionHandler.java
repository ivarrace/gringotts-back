package com.ivarrace.gringotts.application.config;

import com.ivarrace.gringotts.application.dto.ErrorResponse;
import com.ivarrace.gringotts.domain.exception.IllegalGroupTypeException;
import com.ivarrace.gringotts.domain.exception.ObjectAlreadyExistsException;
import com.ivarrace.gringotts.domain.exception.ObjectNotFoundException;
import com.ivarrace.gringotts.domain.exception.UserAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(ObjectNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalGroupTypeException.class)
    public ResponseEntity<ErrorResponse> illegalGroupType(IllegalGroupTypeException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> objectAlreadyExists(ObjectAlreadyExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentials(BadCredentialsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> userAlreadyRegistered(UserAlreadyRegisteredException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
