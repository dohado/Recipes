package com.doha.recipes.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.presentation.dto.RecipeDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdModifiedDateTime", ignore = true)
    Recipe toRecipe(RecipeDto recipeDto);
    @Mapping(target = "date",  source = "recipe.createdModifiedDateTime",
            dateFormat = "yyyy-MM-dd HH:mm:ss")
    RecipeDto toRecipeDto(Recipe recipe);

    List<RecipeDto> toRecipeDtoList(List<Recipe> recipeList);

}
