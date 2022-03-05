package com.ivarrace.gringotts.dto;

import java.util.Date;

public class ErrorResponse {

    private String message;
    private Date date;

    public ErrorResponse(String message){
        this.message = message;
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

}
