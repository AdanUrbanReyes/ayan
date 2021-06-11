package com.ayan.demos.microservices.movies.moviecatalog.controllers;

import com.ayan.demos.microservices.movies.moviecatalog.models.Movie;
import com.ayan.demos.microservices.movies.moviecatalog.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Set<Movie>> findAll() {
        Set<Movie> ms = movieService.findAll();
        HttpStatus hs = ms != null && ms.size() != 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity
                .status(hs)
                .body(ms);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        Movie m = movieService.findById(id);
        HttpStatus hs = m != null ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ResponseEntity
                .status(hs)
                .body(m);
    }

}
