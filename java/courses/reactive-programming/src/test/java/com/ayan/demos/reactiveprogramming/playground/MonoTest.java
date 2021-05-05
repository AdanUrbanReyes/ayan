package com.ayan.demos.reactiveprogramming.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {

    @Test
    public void verifierNoError() {
        Mono<String> mn = Mono.just("Ayan")
                .log();
        StepVerifier.create(mn)
                .expectNext("Ayan")
                .verifyComplete();
    }

    @Test
    public void verifierWhitError() {
        Mono<Object> mn = Mono.error(new RuntimeException("Miss spell name"))
                .log();
        StepVerifier.create(mn)
                .expectError(RuntimeException.class)
                .verify();
    }

}
