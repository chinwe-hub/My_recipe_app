package org.example.controller;

import org.example.model.Recipe;
import org.example.service.RecipeService;
import org.example.util.ConsoleInput;

import java.util.List;


public class RecipeController {
    private final ConsoleInput input;
    private final RecipeService recipeService;

    public RecipeController(ConsoleInput input, RecipeService recipeService) {
        this.input = input;
        this.recipeService = recipeService;
    }

    public void browseRecipes() {
        System.out.println("\n--- Available Recipes ---");
        recipeService.getAllRecipes().forEach(recipe ->
                System.out.printf("%d. %s (%s)%n", recipe.getId(), recipe.getName(), recipe.getCategory()));
    }

    public void searchRecipes() {
        String term = input.readNonBlank("Enter recipe name to search: ");
        List<Recipe> results = recipeService.searchByName(term);
        if (results.isEmpty()) {
            System.out.println("No recipes matched your search.");
        } else {
            printRecipeList(results);
        }
    }

    private void printRecipeList(List<Recipe> recipes) {
        recipes.forEach(recipe -> System.out.printf("%d. %s (%s)%n",
                recipe.getId(), recipe.getName(), recipe.getCategory()));
    }
}
