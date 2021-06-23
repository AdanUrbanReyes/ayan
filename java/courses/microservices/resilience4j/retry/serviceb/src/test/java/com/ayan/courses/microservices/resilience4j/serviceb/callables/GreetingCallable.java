package com.ayan.courses.microservices.resilience4j.serviceb.callables;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.concurrent.Callable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class GreetingCallable implements Callable<Integer> {

    private MockMvc mockMvc;
    private String name;

    public GreetingCallable(MockMvc mockMvc, String name) {
        this.mockMvc = mockMvc;
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        RequestBuilder rb = get("/greeting/" + name);
        return mockMvc.perform(rb)
                .andReturn()
                .getResponse()
                .getStatus();
    }
}
