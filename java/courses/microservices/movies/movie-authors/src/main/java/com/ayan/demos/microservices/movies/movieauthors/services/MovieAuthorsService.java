package com.ayan.demos.microservices.movies.movieauthors.services;

import com.ayan.demos.microservices.movies.movieauthors.entities.Author;
import com.ayan.demos.microservices.movies.movieauthors.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieAuthorsService {

    @Autowired
    private MovieRepository movieRepository;

    public Set<Author> findByMovie(Long movieId) {
        return movieRepository.findById(movieId).get()
                .getAuthors().stream().collect(Collectors.toSet());
    }

}
