package com.ayan.demos.microservices.movies.movieinformation.controllers;

import com.ayan.demos.microservices.movies.movieinformation.entities.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Log4j2
public class MovieControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Movie DUMMY_MOVIE = new Movie(null, "Dummy Movie");

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void happyPathCreate() throws Exception {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String m = OBJECT_MAPPER.writeValueAsString(DUMMY_MOVIE);
        RequestBuilder rq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(m);
        mockMvc.perform(rq)
                .andExpect(status().isCreated())
                .andExpect(header().string("message", ""))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andReturn();
    }

    @Test
    @Order(2)
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
        Optional<Movie> om = ms.stream().filter(m -> DUMMY_MOVIE.getName().equals(m.getName())).findAny();
        Assertions.assertTrue(om.isPresent());
        DUMMY_MOVIE.setId(om.get().getId());
    }

    @Test
    @Order(3)
    public void happyPathFindById() throws Exception {
        RequestBuilder rq = get("/movies/" + DUMMY_MOVIE.getId());
        mockMvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andExpect(jsonPath("$.name", is(DUMMY_MOVIE.getName())))
                .andReturn();
    }

    @Test
    @Order(4)
    public void duplicateKeyCreate() throws Exception {
        String m = OBJECT_MAPPER.writeValueAsString(DUMMY_MOVIE);
        RequestBuilder rq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(m);
        mockMvc.perform(rq)
                .andExpect(status().isConflict())
                .andExpect(header().string("message", String.format("Duplicate %d movie id", DUMMY_MOVIE.getId())))
                .andReturn();
    }

    @Test
    @Order(5)
    public void badRequestCreate() throws Exception {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String m = OBJECT_MAPPER.writeValueAsString(new Movie(1L, null));
        RequestBuilder rq = post("/movies")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(m);
        mockMvc.perform(rq)
                .andExpect(status().isBadRequest())
                .andExpect(header().string("message", String.format("Name is required")))
                .andReturn();
    }

}
