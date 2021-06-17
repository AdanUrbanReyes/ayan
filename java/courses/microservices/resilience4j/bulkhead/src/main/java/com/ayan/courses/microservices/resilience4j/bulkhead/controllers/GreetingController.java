package com.ayan.courses.microservices.resilience4j.bulkhead.controllers;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    private static final String FORMAT = "Hello %s";

    @GetMapping
    @Bulkhead(name = "greetingBulkhead", fallbackMethod = "generalFallBack")
    public ResponseEntity<String> general() {
        return ResponseEntity.ok(String.format(FORMAT, ""));
    }

    public ResponseEntity<String> generalFallBack(BulkheadFullException exception) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .header("Retry-After", "7")
                .build();
    }

}
