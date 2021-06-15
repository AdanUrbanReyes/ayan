package com.ayan.demos.microservices.movies.moviecatalog.controllers;

import com.ayan.demos.microservices.movies.moviecatalog.models.Movie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class MovieControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void happyPathFindAll() throws Exception {
        RequestBuilder rq = get("/movies");
        String rs = mockMvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Set<Movie> ms = OBJECT_MAPPER.readValue(rs, new TypeReference<Set<Movie>>() {
        });
        Assertions.assertTrue(ms.size() != 0);
    }

    @Test
    public void happyPathFindById() throws Exception {
        Integer dmi = 1;
        RequestBuilder rq = get("/movies/" + dmi);
        mockMvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andExpect(jsonPath("$.id", is(dmi)))
                .andReturn();
    }

}
