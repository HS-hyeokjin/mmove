package com.move.move.movieinfo.repository;

import com.move.move.movieinfo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByMovieCode(String movieCode);

}
