package org.example;

import org.example.model.Recipe;
import org.example.model.User;
import org.example.repository.ReviewRepository;
import org.example.repository.ReviewStorageRepository;
import org.example.repository.UserRepository;
import org.example.repository.UserStorageRepository;
import org.example.service.AuthenticationService;
import org.example.service.RecipeService;
import org.example.util.ConsoleInput;
import org.example.controller.AuthenticationController;
import org.example.controller.MainMenuController;
import org.example.controller.RecipeController;
import org.example.controller.WelcomeMenuController;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        RecipeService recipeService = new RecipeService(Recipe.createSampleRecipes());
        UserRepository userRepository = new UserStorageRepository();
        ReviewRepository reviewRepository = new ReviewStorageRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        AuthenticationController authenticationController = new AuthenticationController(input, authenticationService);
        RecipeController recipeController = new RecipeController(input, recipeService);
        WelcomeMenuController welcomeMenuController = new WelcomeMenuController(input, authenticationController, recipeController);
        MainMenuController mainMenuController = new MainMenuController(input, recipeController);

        User currentUser = null;
        boolean running = true;

        System.out.println("======================================");
        System.out.println("       RECIPE BOOK APPLICATION");
        System.out.println("======================================");

        while (running) {
            if (currentUser == null) {
                WelcomeMenuController.Result result = welcomeMenuController.showMenu();
                currentUser = result.user();
                running = result.running();
            } else {
                MainMenuController.Result result = mainMenuController.showMenu(currentUser);
                currentUser = result.user();
                running = result.running();
            }
        }

        System.out.println("Thank you for using Recipe Book. Goodbye!");
        scanner.close();
    }
}
