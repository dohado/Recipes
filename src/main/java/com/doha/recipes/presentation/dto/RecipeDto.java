package com.doha.recipes.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record RecipeDto(@NotBlank(message = "Name must not be empty")
                        String name,
                        @NotBlank(message = "Description must not be empty")
                        String description,
                        @Size(min = 1, message = "At least one ingredient should be provided")
                        @NotNull(message = "Please provide ingredients")
                        List<String> ingredients,
                        @Size(min = 1, message = "At least one direction should be provided")
                        @NotNull(message = "Please provide directions")
                        List<String> directions,
                        @NotBlank(message = "Category must not be empty")
                        String category,
                        String date)
{
}
