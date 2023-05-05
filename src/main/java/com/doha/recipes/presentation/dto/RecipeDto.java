package com.doha.recipes.presentation.dto;

import jakarta.validation.constraints.Size;

public record RecipeDto(@Size(min = 4, message = "Minimum name should contain 4 symbols") String name,
                        String description, String ingredients, String directions) {
}
