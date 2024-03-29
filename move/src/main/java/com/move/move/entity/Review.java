package com.move.move.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid")
    private Movie movie;

    private String content;

    private int rating;

}