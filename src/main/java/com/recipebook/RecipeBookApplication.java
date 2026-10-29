package com.recipebook;

import com.recipebook.controller.*;
import com.recipebook.model.Recipe;
import com.recipebook.model.User;
import com.recipebook.repository.ReviewStorageRepository;
import com.recipebook.repository.UserRepository;
import com.recipebook.repository.UserStorageRepository;
import com.recipebook.service.AuthenticationService;
import com.recipebook.service.RecipeService;
import com.recipebook.service.ReviewService;
import com.recipebook.service.ShoppingListService;
import com.recipebook.util.ConsoleInput;
import com.recipebook.util.FileExporter;

import java.nio.file.Path;
import java.util.Scanner;

public class RecipeBookApplication {
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
