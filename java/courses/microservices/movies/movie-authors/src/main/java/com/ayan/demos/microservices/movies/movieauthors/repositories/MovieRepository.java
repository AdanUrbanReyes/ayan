package com.ayan.demos.microservices.movies.movieauthors.repositories;

import com.ayan.demos.microservices.movies.movieauthors.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
