package com.spoofy.aeroclub.common.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExceptionResponse {

    private Date timestamp;

    private String message;

    private Map<String, String> validationMessages;

    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ExceptionResponse(Date timestamp, Map<String, String> validationMessages, String details) {
        this.timestamp = timestamp;
        this.validationMessages = validationMessages;
        this.details = details;
    }
}
