package com.ayan.courses.microservices.resilience4j.ratelimiter.callables;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.concurrent.Callable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GreetingCallable implements Callable<Integer> {

    private MockMvc mockMvc;

    public GreetingCallable(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Override
    public Integer call() throws Exception {
        RequestBuilder rq = get("/greeting");
        return mockMvc.perform(rq)
                .andReturn()
                .getResponse()
                .getStatus();
    }
}
