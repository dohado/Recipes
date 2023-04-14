package com.doha.recipes.presentation.controller;

import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.business.service.RecipeService;
import com.doha.recipes.presentation.dto.RecipeDto;
import com.doha.recipes.presentation.mapper.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeMapper recipeMapper;

    @PostMapping
    public void saveRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.mapRecipeDtoToRecipe(recipeDto);
        recipeService.saveRecipe(recipe);
    }

    @GetMapping("/{name}")
    public RecipeDto getRecipe(@PathVariable String name) {
        Recipe recipe = recipeService.getRecipeByName(name).orElse(new Recipe());
        return recipeMapper.mapRecipeToRecipeDto(recipe);
    }



}
