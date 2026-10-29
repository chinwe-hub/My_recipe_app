package com.recipebook.controller;

import com.recipebook.model.User;
import com.recipebook.util.ConsoleInput;

public class WelcomeMenuController {
    private final ConsoleInput input;
    private final AuthenticationController authenticationController;
    private final RecipeController recipeController;

    public WelcomeMenuController(ConsoleInput input, AuthenticationController authenticationController, RecipeController recipeController) {
        this.input = input;
        this.authenticationController = authenticationController;
        this.recipeController = recipeController;
    }

    public Result showMenu() {
        System.out.println("\nWelcome!");
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        System.out.println("3. Browse recipes");
        System.out.println("0. Exit");
        int choice = input.readInt("Choose an option: ", 0, 3);

        return switch (choice) {
            case 1 -> new Result(authenticationController.signUp(), true);
            case 2 -> new Result(authenticationController.logIn(), true);
            case 3 -> {
                recipeController.browseRecipes();
                yield new Result(null, true);
            }
            case 0 -> new Result(null, false);
            default -> {
                System.out.println("Invalid choice.");
                yield new Result(null, true);
            }
        };
    }

    public record Result(User user, boolean running) { }
}
