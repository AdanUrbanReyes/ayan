package com.ayan.demos.microservices.movies.movieinformation.services;

import com.ayan.demos.microservices.movies.movieinformation.entities.Movie;
import com.ayan.demos.microservices.movies.movieinformation.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Set<Movie> findAll() {
        return movieRepository.findAll().stream().collect(Collectors.toSet());
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie create(Movie movie) {
        try {
            movie = findById(movie.getId()).get();
            throw new DuplicateKeyException(String.format("Duplicate %d movie id", movie.getId()));
        } catch (NoSuchElementException | InvalidDataAccessApiUsageException e) {
            movie = movieRepository.save(movie);
        }
        return movie;
    }

}
