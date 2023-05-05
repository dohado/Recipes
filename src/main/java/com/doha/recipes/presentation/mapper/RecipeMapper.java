package com.doha.recipes.presentation.mapper;

import com.doha.recipes.business.model.Recipe;
import com.doha.recipes.presentation.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

//    RecipeMapper MAPPER = Mappers.getMapper(RecipeMapper.class);

    @Mapping(target = "id", ignore = true)
    Recipe mapRecipeDtoToRecipe(RecipeDto recipeDTO);
    RecipeDto mapRecipeToRecipeDto(Recipe recipe);

}
