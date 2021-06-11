package com.ayan.demos.microservices.movies.moviecatalog.services;

import com.ayan.demos.microservices.movies.moviecatalog.feignclients.MovieAuthorsFeignClient;
import com.ayan.demos.microservices.movies.moviecatalog.feignclients.MovieInformationFeignClient;
import com.ayan.demos.microservices.movies.moviecatalog.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MovieService {

    @Autowired
    private MovieInformationFeignClient movieInformationFeignClient;

    @Autowired
    private MovieAuthorsFeignClient movieAuthorsFeignClient;

    public Set<Movie> findAll() {
        Set<Movie> ms = movieInformationFeignClient.findAll();
        ms.stream().forEach(m -> m.setAuthors(movieAuthorsFeignClient.findByMovie(m.getId())));
        return ms;
    }

    public Movie findById(Long id) {
        Movie m = movieInformationFeignClient.findById(id);
        if (m != null) {
            m.setAuthors(movieAuthorsFeignClient.findByMovie(m.getId()));
        }
        return m;
    }

}