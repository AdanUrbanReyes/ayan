package com.ayan.demos.microservices.movies.movieinformation.controllers;

import com.ayan.demos.microservices.movies.movieinformation.entities.Movie;
import com.ayan.demos.microservices.movies.movieinformation.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<Set<Movie>> findAll() {
        Set<Movie> ms = movieService.findAll();
        HttpStatus hs = ms == null || ms.size() == 0 ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(hs).body(ms);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        Movie m = null;
        HttpStatus hs = HttpStatus.OK;
        try {
            m = movieService.findById(id).get();
        } catch (NoSuchElementException nse) {
            hs = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(hs).body(m);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody @Valid Movie movie) {
        HttpStatus hs = HttpStatus.CREATED;
        String m = "";
        try {
            movie = movieService.create(movie);
        } catch (DuplicateKeyException dke) {
            hs = HttpStatus.CONFLICT;
            m = dke.getMessage();
        }
        return ResponseEntity
                .status(hs)
                .header("message", m)
                .body(movie);
    }

}
