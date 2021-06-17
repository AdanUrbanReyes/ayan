package com.ayan.courses.microservices.resilience4j.ratelimiter.controllers;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    @RateLimiter(name = "generalGreetingRateLimiter", fallbackMethod = "generalFallBack")
    public ResponseEntity<String> general() {
        return ResponseEntity.ok(String.format(FORMAT, ""));
    }

    public ResponseEntity<String> generalFallBack(RequestNotPermitted exception) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .header("Retry-After", "1")
                .build();
    }

}
