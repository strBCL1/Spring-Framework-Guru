package com.example.recipeproject.converters;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());

        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setIngredientName(source.getIngredientName());
        ingredient.setUom(unitOfMeasure.convert(source.getUom()));

        return ingredient;
    }
}
