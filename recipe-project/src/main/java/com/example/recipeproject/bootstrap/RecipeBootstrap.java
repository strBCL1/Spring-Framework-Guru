package com.example.recipeproject.bootstrap;

import com.example.recipeproject.domain.*;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private void checkIfUomsExist(String... UOMs) {
        for (String s : UOMs) {
            Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRepository.findByUom(s);
            optionalUnitOfMeasure.orElseThrow(() -> new RuntimeException("No such UOM found!"));
        }
    }

    private void checkIfCategoriesExist(String... descriptions) {
        for (String s : descriptions) {
            Optional<Category> categoryRepositoryOptional = categoryRepository.findByCategoryName(s);
            categoryRepositoryOptional.orElseThrow(() -> new RuntimeException("No such category found!"));
        }
    }

    private List<Recipe> getRecipes() {
        // Check if all UOMs exist
        checkIfUomsExist("Teaspoon", "Tablespoon", "Cup", "Pinch", "Ounce", "Piece");

        // Check if all categories exist
        checkIfCategoriesExist("American", "Italian", "Mexican", "Fast Food");

        List<Recipe> recipes = new ArrayList<>(2);

        // Guacamole Recipe
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("guacamole description");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(10);
        guacamoleRecipe.setServings(3);
        guacamoleRecipe.setURL("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("Cut the avocado, Mash the avocado flesh, Add remaining ingredients to taste, Serve immediately");

//         Notes guacamoleNotes = new Notes();
//         guacamoleNotes.setNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
//                        "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
//                        "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
//                        "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
//                        "\n" +
//                        "\n" +
//                        "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");
//         guacamoleNotes.setRecipe(guacamoleRecipe);
//         guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.setNotes(new Notes("""
                For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.
                Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.
                The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.
                To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.


                Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws"""));

        guacamoleRecipe.setDifficulty(Difficulty.EASY);

        Optional<Category> americanCategoryOptional = categoryRepository.findByCategoryName("American");
        Category americanCategory = americanCategoryOptional.get();
        guacamoleRecipe.getCategories().add(americanCategory);

        Optional<UnitOfMeasure> pieceOptionalUnitOfMeasure = unitOfMeasureRepository.findByUom("Piece");
        UnitOfMeasure pieceUnitOfMeasure = pieceOptionalUnitOfMeasure.get();
        guacamoleRecipe.addIngredient(new Ingredient("avocado", 2.0, pieceUnitOfMeasure));
        guacamoleRecipe.addIngredient(new Ingredient("tomato", 0.5, pieceUnitOfMeasure));
        guacamoleRecipe.addIngredient(new Ingredient("chili", 2.0, pieceUnitOfMeasure));


        // Tacos Recipe
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("tacos description");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(15);
        tacosRecipe.setServings(5);
        tacosRecipe.setURL("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacosRecipe.setDirections("Prepare a gas or charcoal grill for medium-high, " +
                "Make the marinade and coat the chicken, " +
                "Grill the chicken, Warm the tortillas, Assemble the tacos");

        tacosRecipe.setNotes(new Notes("""
                We have a family motto and it is this: Everything goes better in a tortilla.
                Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.
                Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!
                First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.
                Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!


                Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ"""));

        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByCategoryName("Mexican");
        Category mexicanCategory = mexicanCategoryOptional.get();
        tacosRecipe.getCategories().add(mexicanCategory);

        Optional<UnitOfMeasure> teaspoonOptionalUnitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");
        UnitOfMeasure teaspoonUnitOfMeasure = teaspoonOptionalUnitOfMeasure.get();
        tacosRecipe.addIngredient(new Ingredient("dried oregano", 1.0, teaspoonUnitOfMeasure));
        tacosRecipe.addIngredient(new Ingredient("sugar", 1.0, teaspoonUnitOfMeasure));
        tacosRecipe.addIngredient(new Ingredient("orange zest", 1.0, teaspoonUnitOfMeasure));

        recipes.add(guacamoleRecipe);
        recipes.add(tacosRecipe);

        return recipes;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }
}
