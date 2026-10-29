package com.recipebook.controller;

import com.recipebook.model.Recipe;
import com.recipebook.service.RecipeService;
import com.recipebook.util.ConsoleInput;
import com.recipebook.util.FileExporter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


public class RecipeController {
    private final ConsoleInput input;
    private final RecipeService recipeService;
    private final FileExporter fileExporter;

    public RecipeController(ConsoleInput input, RecipeService recipeService,
                            FileExporter fileExporter) {
        this.input = input;
        this.recipeService = recipeService;
        this.fileExporter = fileExporter;
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

    public void saveRecipe() {
        Optional<Recipe> recipe = chooseRecipe();
        if (recipe.isEmpty()) return;
        try {
            Path path = fileExporter.exportRecipe(recipe.get());
            System.out.println("Recipe saved to: " + path);
        } catch (IOException ex) {
            System.out.println("Could not save recipe: " + ex.getMessage());
        }
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
