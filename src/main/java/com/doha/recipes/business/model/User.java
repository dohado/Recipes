package com.doha.recipes.business.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(nullable = false, unique = true)
    @Email(message = "Please provide valid email address", regexp = "\\w+@\\w+\\.\\w+")
    @NotBlank(message = "Email should not be blank")
    private String email;
    @Column(nullable = false)
    @Size(min = 8, message = "Password minimum length is 8 characters")
    @NotBlank(message = "Email should not be blank")
    private String password;
    private String role = "USER";
//    @OneToMany(mappedBy = "user")
//    List<Recipe> recipeList;

}
