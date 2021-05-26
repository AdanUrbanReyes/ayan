package com.ayan.demos.helloworld.controllers;

import com.ayan.demos.helloworld.models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("core")
@Log4j2
public class CoreController {

    private static final String GREETING_FORMAT = "%s said... Hello %s!!!";

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("say-hello")
    public ResponseEntity<String> sayHello() {
        log.info("saying hello");
        return ResponseEntity.ok(String.format(GREETING_FORMAT, applicationName, ""));
    }

    @PostMapping("say-hello")
    public ResponseEntity<String> sayHello(@RequestBody User user) {
        log.info(user);
        return ResponseEntity.ok(String.format(GREETING_FORMAT, applicationName, user.getName()));
    }
}
