package com.move.move.review.repository;

import com.move.move.movieinfo.entity.Movie;
import com.move.move.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);

}
