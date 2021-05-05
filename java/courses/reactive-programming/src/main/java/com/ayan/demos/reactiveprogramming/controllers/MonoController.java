package com.ayan.demos.reactiveprogramming.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mono")
public class MonoController {

    @GetMapping
    public Mono<Integer> get() {
        return Mono.just(1)
                .log();
    }

}
