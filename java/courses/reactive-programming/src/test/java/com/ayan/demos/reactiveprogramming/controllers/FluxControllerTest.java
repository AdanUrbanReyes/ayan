package com.ayan.demos.reactiveprogramming.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest
public class FluxControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void get() {
        Flux<Integer> ns = webTestClient
                .get()
                .uri("/flux")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(Integer.class)
                .getResponseBody();
        StepVerifier.create(ns)
                .expectSubscription()
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }

    @Test
    public void infinity() {
        Flux<Long> ns = webTestClient
                .get()
                .uri("/flux/infinity")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(Long.class)
                .getResponseBody();
        StepVerifier.create(ns)
                .expectSubscription()
                .expectNext(0L, 1L, 2L)
                .thenCancel()
                .verify();
    }


}
