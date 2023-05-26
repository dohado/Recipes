package com.doha.recipes.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.business.service.RecipeService;
import com.doha.recipes.business.service.UserService;
import com.doha.recipes.exceptions.MoreThanOneParameterException;
import com.doha.recipes.exceptions.NoParametersException;
import com.doha.recipes.exceptions.RecipeNotFoundException;
import com.doha.recipes.presentation.dto.RecipeDto;
import com.doha.recipes.presentation.mapper.RecipeMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeMapper recipeMapper;
    @Autowired
    UserService userService;

    @PostMapping("/new")
    public Map<String, Long> saveRecipe(@RequestBody @Valid RecipeDto recipeDto, @AuthenticationPrincipal UserDetails userDetails) {
        com.doha.recipes.business.model.User user = userService.findUserByEmail(userDetails.getUsername());
        Recipe recipe = recipeMapper.toRecipe(recipeDto);
        recipe.setUser(user);
        Recipe storedRecipe = recipeService.saveRecipe(recipe);
        return Map.of("id", storedRecipe.getId());
    }

    @GetMapping("/{id}")
    public RecipeDto getRecipe(@PathVariable long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipeMapper.toRecipeDto(recipe);
    }

    @GetMapping("/search")
    public List<RecipeDto> getRecipeBy(@RequestParam(required = false) String category,
                                       @RequestParam(required = false) String name) {
        if (category == null) {
            if (name == null) {
                throw new NoParametersException();
            } else {
                List<Recipe> recipeList = recipeService.getRecipeByName(name);
                return recipeList.isEmpty() ? Collections.emptyList() : recipeMapper.toRecipeDtoList(recipeList);
            }
        } else {
            if (name == null) {
                List<Recipe> recipeList = recipeService.getRecipeByCategory(category);
                return recipeList.isEmpty() ? Collections.emptyList() : recipeMapper.toRecipeDtoList(recipeList);
            }
        }
        throw new MoreThanOneParameterException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (recipeService.existsById(id)) {
            Recipe recipe = recipeService.getRecipeById(id);
            if (recipe.getUser() != null &&
                    recipe.getUser().equals(userService.findUserByEmail(userDetails.getUsername()))) {
                recipeService.deleteRecipe(id);
            } else throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "You are not allowed to delete other users recipes");
        } else throw new RecipeNotFoundException(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @RequestBody @Valid RecipeDto recipeDto,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Recipe recipe = recipeService.getRecipeById(id);
        Recipe updatedRecipe = recipeMapper.toRecipe(recipeDto);
        updatedRecipe.setId(id);
        updatedRecipe.setUser(userService.findUserByEmail(userDetails.getUsername()));
        if (recipe.getUser() != null && (updatedRecipe.getUser()).equals(recipe.getUser())) {
            if (!Objects.equals(recipe, updatedRecipe)) {
                recipeService.saveRecipe(updatedRecipe);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "You are not allowed to change other users recipes");
        }

    }


}
