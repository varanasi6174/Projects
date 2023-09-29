package com.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    
    @Id
    private Long bid;
    
    @Column(length=20, nullable = true)
    private String title;
    
    @Column(length=20, nullable = true)
    private String author;
    
    @Column(length=5, nullable = true)
    private boolean borrowed;
    
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User borrowedBy;
}
