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
    @ElementCollection
    @CollectionTable(
            name="INGREDIENTS",
            joinColumns=@JoinColumn(name="RECIPE_ID")
    )
    @Column(name="INGREDIENT", nullable = false)
    private List<String> ingredients;
    @ElementCollection
    @CollectionTable(
            name="DIRECTIONS",
            joinColumns=@JoinColumn(name="RECIPE_ID")
    )
    @Column(name="DIRECTION", nullable = false)
    private List<String> directions;
    @Column(nullable = false)
    private String category;
    @UpdateTimestamp
    @Column(name = "CREATED_MODIFIED_DATETIME")
    private LocalDateTime createdModifiedDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
