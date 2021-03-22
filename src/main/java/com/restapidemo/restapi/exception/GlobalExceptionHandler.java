package com.restapidemo.restapi.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ConfigDataResourceNotFoundException exception,
                                                    WebRequest request) {
        ExceptionData exceptionData = new ExceptionData(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {

        ExceptionData exceptionData = new ExceptionData(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
