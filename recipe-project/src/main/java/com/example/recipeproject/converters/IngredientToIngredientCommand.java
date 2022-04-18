package com.example.recipeproject.converters;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();

        ingredientCommand.setId(source.getId());

        if (source.getRecipe() != null) {
            ingredientCommand.setId(source.getRecipe().getId());
        }

        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setIngredientName(source.getIngredientName());
        ingredientCommand.setUom(uomConverter.convert(source.getUom()));

        return ingredientCommand;
    }
}
