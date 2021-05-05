package com.ayan.demos.reactiveprogramming.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("flux")
public class FluxController {

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> get() {
        return Flux.just(1, 2, 3, 4)
                .delayElements(Duration.ofSeconds(3))
                .log();
    }

    @GetMapping(path = "/infinity", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> infinity() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }

}
