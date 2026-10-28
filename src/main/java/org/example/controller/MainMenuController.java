package org.example.controller;

import org.example.model.User;
import org.example.util.ConsoleInput;

public class MainMenuController {
    private final ConsoleInput input;
    private final RecipeController recipeController;
    private final ReviewController reviewController;
    private final ShoppingListController shoppingListController;
    public MainMenuController(ConsoleInput input, RecipeController recipeController,
                              ShoppingListController shoppingListController,  ReviewController reviewController) {
        this.input = input;
        this.recipeController = recipeController;
        this.shoppingListController = shoppingListController;
        this.reviewController = reviewController;
    }

    public Result showMenu(User currentUser) {
        System.out.println("\nHello, " + currentUser.getUsername() + "!");
        System.out.println("1. View all recipes");
        System.out.println("2. Search recipes by name");
        System.out.println("3. View recipe details");
        System.out.println("4. Generate shopping list");
        System.out.println("5. Save recipe to text file");
        System.out.println("6. Rate and review a recipe");
        System.out.println("7. View recipe reviews");
        System.out.println("8. Log out");
        System.out.println("0. Exit");
        int choice = input.readInt("Choose an option: ", 0, 8);

        switch (choice) {
            case 1 -> recipeController.browseRecipes();
            case 2 -> recipeController.searchRecipes();
            case 3 -> recipeController.viewRecipeDetails();
            case 4 -> shoppingListController.generateShoppingList();
            case 5 -> recipeController.saveRecipe();
            case 6 -> reviewController.submitReview(currentUser);
            case 7 -> reviewController.viewReviews();
            case 8 -> {
                System.out.println("You have logged out.");
                return new Result(null, true);
            }
            case 0 -> { return new Result(currentUser, false); }
            default -> System.out.println("Invalid choice.");
        }
        return new Result(currentUser, true);
    }

    public record Result(User user, boolean running) { }
}
