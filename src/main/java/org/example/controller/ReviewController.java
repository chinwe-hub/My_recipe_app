package org.example.controller;

import org.example.model.Recipe;
import org.example.model.Review;
import org.example.model.User;
import org.example.service.ReviewService;
import org.example.util.ConsoleInput;

import java.util.Optional;

public class ReviewController {
    private final ConsoleInput input;
    private final RecipeController recipeController;
    private final ReviewService reviewService;

    public ReviewController(ConsoleInput input, RecipeController recipeController, ReviewService reviewService) {
        this.input = input;
        this.recipeController = recipeController;
        this.reviewService = reviewService;
    }

    public void submitReview(User currentUser) {
        Optional<Recipe> recipe = recipeController.chooseRecipe();
        if (recipe.isEmpty()) return;
        int rating = input.readInt("Enter rating from 1 to 5: ", 1, 5);
        String comment = input.readNonBlank("Write a short review (max 300 characters): ");
        try {
            Review review = reviewService.submitReview(currentUser, recipe.get().getId(), rating, comment);
            System.out.println("Thank you! Your review was saved: " + review.getRating() + "/5.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not save review: " + ex.getMessage());
        }
    }
}
