package com.ayan.demos.reactiveprogramming.configurations;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@SpringBootTest
@AutoConfigureWebTestClient
public class SampleRouterFunctionConfigurationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getFlux() {
        Flux<Integer> ns = webTestClient
                .get()
                .uri("/flux/functional")
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
    public void getInfinityFlux() {
        Flux<Long> ns = webTestClient
                .get()
                .uri("/flux/functional/infinity")
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

    @Test
    public void getMono() {
        webTestClient.get()
                .uri("/mono/functional")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Integer.class)
                .consumeWith(r -> Assertions.assertEquals(1, r.getResponseBody()));
    }

}
