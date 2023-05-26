package com.doha.recipes.persistence;

import com.doha.recipes.business.model.Recipe;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepo extends CrudRepository<Recipe, Long> {

    public Optional<List<Recipe>> findByNameContainingIgnoreCase(String name, Sort sort);
    public Optional<List<Recipe>> findByCategoryIgnoreCase(String category, Sort sort);

}
