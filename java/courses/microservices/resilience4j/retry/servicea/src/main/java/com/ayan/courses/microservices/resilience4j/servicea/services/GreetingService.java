package com.ayan.courses.microservices.resilience4j.servicea.services;

import com.ayan.courses.microservices.resilience4j.servicea.feignclients.GreetingFeignClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Autowired
    private GreetingFeignClient greetingFeignClient;

    @Retry(name = "greetingRetry", fallbackMethod = "generalFallBack")
    public String general() {
        return greetingFeignClient.general("!!!");
    }

    public String generalFallBack(Throwable exception) {
        return "Cached Hello!!!";
    }

}
