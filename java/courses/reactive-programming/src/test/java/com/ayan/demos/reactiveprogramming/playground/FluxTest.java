package com.ayan.demos.reactiveprogramming.playground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    @Test
    public void verifierNoError() {
        Flux<String> mn = Flux.just("Ayan", "Adan")
                .concatWith(Flux.just("Alan"));
        mn.subscribe(System.out::println, System.out::println, () -> System.out.println("Completed the work; must to be show"));
        StepVerifier.create(mn)
                .expectNext("Ayan")
                .expectNext("Adan")
                .expectNext("Alan")
                .verifyComplete();
    }

    @Test
    public void verifierWithError() {
        Flux<String> fn = Flux.just("Alejandra", "Ariatna", "Amairani")
                .concatWith(Flux.error(new RuntimeException("Miss spell name")))
                .concatWith(Flux.just("After Error; not should be send"))
                .log();
        fn.subscribe(System.out::println, System.out::println, () -> System.out.println("Completed the work; not show cause the exception"));
        StepVerifier.create(fn)
                .expectNext("Alejandra")
                .expectNextCount(2)
                .expectErrorMessage("Miss spell name")
                .verify();
    }

}
