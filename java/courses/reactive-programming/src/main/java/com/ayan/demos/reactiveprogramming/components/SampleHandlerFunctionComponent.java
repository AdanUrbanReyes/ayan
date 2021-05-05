package com.ayan.demos.reactiveprogramming.components;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class SampleHandlerFunctionComponent {

    public Mono<ServerResponse> getFlux(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Flux.just(1, 2, 3, 4).delayElements(Duration.ofSeconds(3)).log()
                        , Integer.class);
    }

    public Mono<ServerResponse> getInfinityFlux(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Flux.interval(Duration.ofSeconds(1)).log()
                        , Integer.class);
    }

    public Mono<ServerResponse> getMono(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(1).log()
                        , Integer.class);
    }

}
