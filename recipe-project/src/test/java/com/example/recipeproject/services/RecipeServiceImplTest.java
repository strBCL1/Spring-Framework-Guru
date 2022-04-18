package com.example.recipeproject.services;

import com.example.recipeproject.converters.RecipeCommandToRecipe;
import com.example.recipeproject.converters.RecipeToRecipeCommand;
import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecipeServiceImplTest {
    RecipeService recipeService;

    RecipeRepository recipeRepository;

    RecipeCommandToRecipe recipeCommandToRecipe;
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    public void setUp() throws Exception {
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned);
    }
}
