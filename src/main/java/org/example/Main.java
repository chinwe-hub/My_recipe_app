package org.example;

import org.example.model.Recipe;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.UserStorageRepository;
import org.example.service.AuthenticationService;
import org.example.service.RecipeService;
import org.example.util.ConsoleInput;
import org.example.view.AuthenticationView;
import org.example.view.MainMenuView;
import org.example.view.RecipeView;
import org.example.view.WelcomeMenuView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        RecipeService recipeService = new RecipeService(Recipe.createSampleRecipes());
        UserRepository userRepository = new UserStorageRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        AuthenticationView authenticationView = new AuthenticationView(input, authenticationService);
        RecipeView recipeView = new RecipeView(input, recipeService);
        WelcomeMenuView welcomeMenuView = new WelcomeMenuView(input, authenticationView, recipeView);
        MainMenuView mainMenuView = new MainMenuView(input, recipeView);

        User currentUser = null;
        boolean running = true;

        System.out.println("======================================");
        System.out.println("       RECIPE BOOK APPLICATION");
        System.out.println("======================================");

        while (running) {
            if (currentUser == null) {
                WelcomeMenuView.Result result = welcomeMenuView.showMenu();
                currentUser = result.user();
                running = result.running();
            } else {
                MainMenuView.Result result = mainMenuView.showMenu(currentUser);
                currentUser = result.user();
                running = result.running();
            }
        }

        System.out.println("Thank you for using Recipe Book. Goodbye!");
        scanner.close();
    }
}
