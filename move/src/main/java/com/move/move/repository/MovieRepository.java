package com.move.move.repository;

import com.move.move.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByMovieCode(String movieCode);

}
