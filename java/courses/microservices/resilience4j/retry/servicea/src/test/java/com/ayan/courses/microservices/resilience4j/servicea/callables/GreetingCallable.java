package com.ayan.courses.microservices.resilience4j.servicea.callables;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.concurrent.Callable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GreetingCallable implements Callable<String> {

    private MockMvc mockMvc;

    public GreetingCallable(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @Override
    public String call() throws Exception {
        RequestBuilder rb = get("/greeting");
        return mockMvc.perform(rb)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
