package com.ayan.courses.microservices.resilience4j.servicea.controllers;

import com.ayan.courses.microservices.resilience4j.servicea.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping
    public ResponseEntity<String> general() {
        String g = greetingService.general();
        return ResponseEntity.ok(g);
    }

}
