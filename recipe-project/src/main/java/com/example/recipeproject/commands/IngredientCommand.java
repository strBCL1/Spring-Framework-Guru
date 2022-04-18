package com.example.recipeproject.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String ingredientName;
    private Double amount;
    private UnitOfMeasureCommand uom;
}
