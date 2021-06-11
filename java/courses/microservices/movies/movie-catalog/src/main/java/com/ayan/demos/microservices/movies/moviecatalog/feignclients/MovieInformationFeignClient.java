package com.ayan.demos.microservices.movies.moviecatalog.feignclients;

import com.ayan.demos.microservices.movies.moviecatalog.models.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "${feignClient.movieInformation.name}"
        , path = "${feignClient.movieInformation.url}")
public interface MovieInformationFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    Set<Movie> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    Movie findById(@PathVariable("id") Long id);

}
