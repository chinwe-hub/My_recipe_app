package org.example.view;

import org.example.model.User;
import org.example.util.ConsoleInput;

public class WelcomeMenuView {
    private final ConsoleInput input;
    private final AuthenticationView authenticationView;
    private final RecipeView recipeView;

    public WelcomeMenuView(ConsoleInput input, AuthenticationView authenticationView, RecipeView recipeView) {
        this.input = input;
        this.authenticationView = authenticationView;
        this.recipeView = recipeView;
    }

    public Result showMenu() {
        System.out.println("\nWelcome!");
        System.out.println("1. Sign up");
        System.out.println("2. Log in");
        System.out.println("3. Browse recipes");
        System.out.println("0. Exit");
        int choice = input.readInt("Choose an option: ", 0, 3);

        return switch (choice) {
            case 1 -> new Result(authenticationView.signUp(), true);
            case 2 -> new Result(authenticationView.logIn(), true);
            case 3 -> {
                recipeView.browseRecipes();
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
