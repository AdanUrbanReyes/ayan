package com.ayan.demos.microservices.movies.moviecatalog.controllers;

import feign.RetryableException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RetryableException.class})
    protected ResponseEntity<Object> connectException(RetryableException exception, WebRequest request) {
        HttpHeaders hh = new HttpHeaders();
        if (exception.getCause() instanceof ConnectException) {
            hh.add("message"
                    , String.format("The %s REST API is unavailable"
                            , exception.request().url()
                    ));
        } else if (exception.getCause() instanceof SocketTimeoutException) {
            hh.add("message"
                    , String.format("The %s REST API took more time to respond than the http timeout available"
                            , exception.request().url()
                    ));
        }
        return handleExceptionInternal(exception
                , null
                , new HttpHeaders()
                , HttpStatus.GATEWAY_TIMEOUT
                , request);
    }

}
