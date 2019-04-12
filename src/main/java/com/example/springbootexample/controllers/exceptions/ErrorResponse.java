package com.example.springbootexample.controllers.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String date;
    private int status;
    private String message;
    private String details;

    public ErrorResponse(Date date, int status, String message, String details) {
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
