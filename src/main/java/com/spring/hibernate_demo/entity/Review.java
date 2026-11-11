package com.spring.hibernate_demo.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Initializes an review table to be created in the DB with
 * its columns and specified.
 */
@Entity 
@Table(name="review")
public class Review {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column 
    private int id;

    @Column(name="comments")
    private String comments;

    public Review() {

    }
    
    public Review(String comments) {
        this.comments = comments;
    }

    //to convert java object display to string
    @Override
    public String toString() {
    return "Review [id=" + id +
            ", comment=" + comments + "]";
    }

}
