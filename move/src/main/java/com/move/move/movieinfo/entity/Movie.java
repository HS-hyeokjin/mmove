package com.move.move.movieinfo.entity;

import com.move.move.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String movieCode;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;
}
