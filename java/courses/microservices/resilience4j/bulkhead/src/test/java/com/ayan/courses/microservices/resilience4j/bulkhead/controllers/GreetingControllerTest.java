package com.ayan.courses.microservices.resilience4j.bulkhead.controllers;


import com.ayan.courses.microservices.resilience4j.bulkhead.callables.GreetingCallable;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class GreetingControllerTest {

    private static final Integer MAX_REQUEST = 4;

    @Autowired
    private MockMvc mockMvc;

    @Test
    /**
     * Inside of this test we are validating that
     * with the configuration of <code>resilience4j</code>;
     * which basically accept just two request running in parallel
     * And we are sending <code>MAX_REQUEST</code> in a loop
     * so ideally we hope get the OK status on just two request
     * and for all the others the <code>HttpStatus.SERVICE_UNAVAILABLE</code>
     */
    public void happyPath() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(MAX_REQUEST);
        List<Future<Integer>> fs = new ArrayList<>();
        List<Integer> ss = new ArrayList<>();
        for (int i = 0; i < MAX_REQUEST; i++) {
            fs.add(es.submit(new GreetingCallable(mockMvc)));
        }
        for (int i = 0; i < MAX_REQUEST; i++) {
            ss.add(fs.get(i).get());
        }
        es.shutdown();
        Assertions.assertTrue(ss.stream().filter(s -> s.equals(HttpStatus.SERVICE_UNAVAILABLE.value())).findAny().isPresent());
    }

}
