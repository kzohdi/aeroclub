package com.spoofy.aeroclub.common.exception.handler;

import com.spoofy.aeroclub.common.exception.NotFoundException;
import com.spoofy.aeroclub.common.exception.ServerException;
import com.spoofy.aeroclub.common.exception.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandling(MethodArgumentNotValidException exception, WebRequest request) {
        log.error("Method argument not valid", exception);
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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundExceptionHandling(Exception exception, WebRequest request) {
        log.error("Not found exception", exception);
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<?> serverExceptionHandling(Exception exception, WebRequest request) {
        log.error("Server exception", exception);
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        log.error("Generic exception", exception);
        return new ResponseEntity<>(new ExceptionResponse(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
