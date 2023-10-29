package com.spoofy.aeroclub.exception.handler;

import com.spoofy.aeroclub.exception.NotFoundException;
import com.spoofy.aeroclub.exception.ServerException;
import com.spoofy.aeroclub.exception.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandling(MethodArgumentNotValidException exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(new Date(), getValidationErrors(exception), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> getValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });
        return validationErrors;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServerException.class)
    public ResponseEntity<?> serverExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
