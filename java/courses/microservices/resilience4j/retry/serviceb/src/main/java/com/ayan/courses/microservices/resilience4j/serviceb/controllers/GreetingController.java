package com.ayan.courses.microservices.resilience4j.serviceb.controllers;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    public static final String FORMAT = "Hello %s";

    @Value("${resilience4j.ratelimiter.configs.default.limitRefreshPeriod}")
    private String rateLimiterRefreshPeriod;

    @GetMapping("{name}")
    @RateLimiter(name = "greetingRateLimit", fallbackMethod = "generalFallBack")
    public ResponseEntity<String> general(@PathVariable String name) {
        return ResponseEntity.ok(String.format(FORMAT, name));
    }

    public ResponseEntity<String> generalFallBack(String name, RequestNotPermitted exception) {
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .header("Retry-After", rateLimiterRefreshPeriod)
                .build();
    }

}
