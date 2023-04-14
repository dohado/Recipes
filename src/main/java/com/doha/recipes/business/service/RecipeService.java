package com.doha.recipes.business.service;

import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.persistence.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    RecipeRepo recipeRepo;

    public RecipeService(@Autowired RecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Optional<Recipe> getRecipeByName(String name) {
        return recipeRepo.findRecipeByName(name);
    }

    public Recipe saveRecipe(Recipe toSave) {
        return recipeRepo.save(toSave);
    }

}
