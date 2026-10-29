package com.recipebook.controller;

import com.recipebook.model.Ingredient;
import com.recipebook.model.Recipe;
import com.recipebook.service.ShoppingListService;
import com.recipebook.util.ConsoleInput;
import com.recipebook.util.FileExporter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class ShoppingListController {
    private final ConsoleInput input;
    private final RecipeController recipeController;
    private final ShoppingListService shoppingListService;
    private final FileExporter fileExporter;

    public ShoppingListController(ConsoleInput input, RecipeController recipeController,
                            ShoppingListService shoppingListService, FileExporter fileExporter) {
        this.input = input;
        this.recipeController = recipeController;
        this.shoppingListService = shoppingListService;
        this.fileExporter = fileExporter;
    }

    public void generateShoppingList() {
        Optional<Recipe> recipe = recipeController.chooseRecipe();
        if (recipe.isEmpty()) return;
        List<Ingredient> shoppingList = shoppingListService.generate(recipe.get());
        System.out.println("\n--- Shopping List: " + recipe.get().getName() + " ---");
        shoppingList.forEach(item -> System.out.println("- " + item));
        if (input.readYesNo("Save this list to a .txt file? (y/n): ")) {
            try {
                Path path = fileExporter.exportShoppingList(recipe.get(), shoppingList);
                System.out.println("Shopping list saved to: " + path);
            } catch (IOException ex) {
                System.out.println("Could not save shopping list: " + ex.getMessage());
            }
        }
    }
}
