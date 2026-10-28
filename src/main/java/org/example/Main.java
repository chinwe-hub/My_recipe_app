package org.example;

import org.example.controller.*;
import org.example.model.Recipe;
import org.example.model.User;
import org.example.repository.ReviewRepository;
import org.example.repository.ReviewStorageRepository;
import org.example.repository.UserRepository;
import org.example.repository.UserStorageRepository;
import org.example.service.AuthenticationService;
import org.example.service.RecipeService;
import org.example.service.ReviewService;
import org.example.service.ShoppingListService;
import org.example.util.ConsoleInput;
import org.example.util.FileExporter;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        RecipeService recipeService = new RecipeService(Recipe.createSampleRecipes());
        UserRepository userRepository = new UserStorageRepository();
        ReviewStorageRepository reviewStorageRepository = new ReviewStorageRepository();
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        ReviewService reviewService = new ReviewService(reviewStorageRepository, recipeService);
        ShoppingListService shoppingListService = new ShoppingListService();
        FileExporter fileExporter = new FileExporter(Path.of("exports"));

        AuthenticationController authenticationController = new AuthenticationController(input, authenticationService);
        RecipeController recipeController = new RecipeController(input, recipeService, fileExporter);
        ShoppingListController shoppingListController = new ShoppingListController(input, recipeController,
                shoppingListService, fileExporter);
        ReviewController reviewController = new ReviewController(input, recipeController, reviewService);
        WelcomeMenuController welcomeMenuController = new WelcomeMenuController(input, authenticationController, recipeController);
        MainMenuController mainMenuController = new MainMenuController(input, recipeController, shoppingListController, reviewController);

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
