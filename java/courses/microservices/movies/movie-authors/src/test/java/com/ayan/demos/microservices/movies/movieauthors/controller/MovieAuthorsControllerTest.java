package com.ayan.demos.microservices.movies.movieauthors.controller;

import com.ayan.demos.microservices.movies.movieauthors.entities.Author;
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

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class MovieAuthorsControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void happyPathFindAll() throws Exception {
        RequestBuilder rq = get("/movies/1/authors");
        String rs = mockMvc.perform(rq)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        Set<Author> ms = OBJECT_MAPPER.readValue(rs, new TypeReference<Set<Author>>() {
        });
        Assertions.assertTrue(ms.size() != 0);
    }

}
