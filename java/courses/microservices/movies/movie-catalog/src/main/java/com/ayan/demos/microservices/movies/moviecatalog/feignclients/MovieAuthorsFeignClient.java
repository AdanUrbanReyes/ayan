package com.ayan.demos.microservices.movies.moviecatalog.feignclients;

import com.ayan.demos.microservices.movies.moviecatalog.models.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "${feignClient.movieAuthors.name}"
        , path = "${feignClient.movieAuthors.url}")
public interface MovieAuthorsFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "{movieId}/authors")
    Set<Author> findByMovie(@PathVariable("movieId") Long movieId);

}
