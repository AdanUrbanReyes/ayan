package com.ayan.demos.microservices.movies.movieauthors.controllers;

import com.ayan.demos.microservices.movies.movieauthors.entities.Author;
import com.ayan.demos.microservices.movies.movieauthors.services.MovieAuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("movies/{movieId}/authors")
public class MovieAuthorsController {

    @Autowired
    private MovieAuthorsService movieAuthorsService;

    @GetMapping
    public ResponseEntity<Set<Author>> findByMovie(@PathVariable Long movieId) {
        Set<Author> as = new HashSet<>();
        HttpStatus hs = HttpStatus.OK;
        String m = "";
        try {
            as = movieAuthorsService.findByMovie(movieId);
            if (as == null || as.size() == 0) {
                hs = HttpStatus.NO_CONTENT;
            }
        } catch (NoSuchElementException nse) {
            hs = HttpStatus.NOT_FOUND;
            m = String.format("Movie %d id not found", movieId);
        }
        return ResponseEntity
                .status(hs)
                .header("message", m)
                .body(as);
    }

}
