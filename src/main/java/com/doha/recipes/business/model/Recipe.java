package com.doha.recipes.business.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @ElementCollection
    private List<String> ingredients;
    @Column(nullable = false)
    @ElementCollection
    private List<String> directions;
    @Column(nullable = false)
    private String category;
    @UpdateTimestamp
    private LocalDateTime createdModifiedDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
