package com.liana.coffeeshop_vigo.model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(length = 100)
    private String name;
    @Column(columnDefinition = "DECIMAL(2,1)")
    private Double score;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "coffeeshop_id", nullable = false)
    private Coffeeshop coffeeshop;

    // Constructor
    public Review (){

    }
    // Getters and Setters

    public Integer getReviewId() {
        return reviewId;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
