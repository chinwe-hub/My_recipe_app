package org.example.controller;

import org.example.model.Recipe;
import org.example.service.RecipeService;
import org.example.util.ConsoleInput;

import java.util.List;
import java.util.Optional;


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

    public void viewRecipeDetails() {
        chooseRecipe().ifPresent(this::printRecipe);
    }

    private void printRecipeList(List<Recipe> recipes) {
        recipes.forEach(recipe -> System.out.printf("%d. %s (%s)%n",
                recipe.getId(), recipe.getName(), recipe.getCategory()));
    }

    public Optional<Recipe> chooseRecipe() {
        browseRecipes();
        int id = input.readInt("Enter recipe ID (0 to cancel): ", 0, Integer.MAX_VALUE);
        if (id == 0) {
            return Optional.empty();
        }
        Optional<Recipe> recipe = recipeService.findById(id);
        if (recipe.isEmpty()) {
            System.out.println("No recipe exists with that ID.");
        }
        return recipe;
    }

    public void printRecipe(Recipe recipe) {
        System.out.println("\n--- " + recipe.getName() + " ---");
        System.out.println("Category: " + recipe.getCategory());
        System.out.println("Preparation time: " + recipe.getPreparationMinutes() + " minutes");
        System.out.println("Cooking time: " + recipe.getCookingMinutes() + " minutes");
        System.out.println("Ingredients:");
        recipe.getIngredients().forEach(item -> System.out.println(" - " + item));
        System.out.println("Instructions:");
        for (int i = 0; i < recipe.getInstructions().size(); i++) {
            System.out.println((i + 1) + ". " + recipe.getInstructions().get(i));
        }
    }
}
