package com.ayan.demos.microservices.movies.moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogApplication.class, args);
    }

}
