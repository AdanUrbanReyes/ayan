package com.ayan.demos.reactiveprogramming.configurations;

import com.ayan.demos.reactiveprogramming.components.SampleHandlerFunctionComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SampleRouterFunctionConfiguration {

    @Bean
    public RouterFunction<ServerResponse> route(SampleHandlerFunctionComponent sampleHandlerFunctionComponent) {
        return RouterFunctions
                .route(RequestPredicates.GET("/flux/functional"), sampleHandlerFunctionComponent::getFlux)
                .andRoute(RequestPredicates.GET("/flux/functional/infinity"), sampleHandlerFunctionComponent::getInfinityFlux)
                .andRoute(RequestPredicates.GET("/mono/functional"), sampleHandlerFunctionComponent::getMono);
    }

}
