package com.ivarrace.gringotts.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private Date date;

    public ErrorResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
