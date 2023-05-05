package com.doha.recipes.presentation.controller;

import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.business.service.RecipeService;
import com.doha.recipes.exceptions.RecipeNotFoundException;
import com.doha.recipes.presentation.dto.RecipeDto;
import com.doha.recipes.presentation.mapper.RecipeMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Validated
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeMapper recipeMapper;

    @PostMapping
    public ResponseEntity<List<String>> saveRecipe(@RequestBody @Valid RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.mapRecipeDtoToRecipe(recipeDto);
        recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(List.of("Recipe is saved"));
    }

    @GetMapping("/{name}")
    public RecipeDto getRecipe(@PathVariable @Pattern(regexp = "[\\w\\s]+",
            message = "Please provide valid name containing only letters, numbers, spaces") String name) {
        Recipe recipe = recipeService.getRecipeByName(name)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
        return recipeMapper.mapRecipeToRecipeDto(recipe);
    }



}
