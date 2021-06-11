package com.ayan.demos.microservices.movies.movieinformation.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request) {
        headers.add("message", exception.getFieldError().getDefaultMessage());
        return this.handleExceptionInternal(exception
                , null
                , headers
                , status
                , request);
    }

}

