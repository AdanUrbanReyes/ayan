package com.ayan.demos.reactiveprogramming.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class MonoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void get() {
        webTestClient.get()
                .uri("/mono")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Integer.class)
                .consumeWith(r -> Assertions.assertEquals(1, r.getResponseBody()));
    }

}
