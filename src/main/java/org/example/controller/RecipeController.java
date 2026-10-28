package org.example.controller;

import org.example.service.RecipeService;
import org.example.util.ConsoleInput;


public class RecipeController {
    private final ConsoleInput input;
    private final RecipeService recipeService;

    public RecipeController(ConsoleInput input, RecipeService recipeService) {
        this.input = input;
        this.recipeService = recipeService;
    }

    public void browseRecipes() {
        System.out.println("\n--- Available Recipes ---");
    }

}

