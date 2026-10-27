package org.example.view;

import org.example.service.RecipeService;
import org.example.util.ConsoleInput;


public class RecipeView {
    private final ConsoleInput input;
    private final RecipeService recipeService;

    public RecipeView(ConsoleInput input, RecipeService recipeService) {
        this.input = input;
        this.recipeService = recipeService;
    }

    public void browseRecipes() {
        System.out.println("\n--- Available Recipes ---");
    }

}

