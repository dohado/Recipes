package com.doha.recipes.business.service;

import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.exceptions.RecipeNotFoundException;
import com.doha.recipes.persistence.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    public List<Recipe> getRecipeByName(String name) {
        return recipeRepo.findByNameContainingIgnoreCase(name, sortByDate().descending())
                .orElse(Collections.emptyList());
    }

    public List<Recipe> getRecipeByCategory(String category) {
        return recipeRepo.findByCategoryIgnoreCase(category, sortByDate().descending())
                .orElse(Collections.emptyList());
    }

    public Recipe getRecipeById(long id) {
        return recipeRepo.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }


    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    public void deleteRecipe(long id) {
        recipeRepo.deleteById(id);
    }

    public boolean existsById(long id) {
        return recipeRepo.existsById(id);
    }

    private Sort sortByDate() {
        return Sort.sort(Recipe.class).by(Recipe::getCreatedModifiedDateTime);
    }

}
