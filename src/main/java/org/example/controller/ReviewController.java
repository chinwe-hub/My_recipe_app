package org.example.controller;

import org.example.service.ReviewService;
import org.example.util.ConsoleInput;

public class ReviewController {
    private final ConsoleInput input;
    private final RecipeController recipeController;
    private final ReviewService reviewService;

    public ReviewController(ConsoleInput input, RecipeController recipeController, ReviewService reviewService) {
        this.input = input;
        this.recipeController = recipeController;
        this.reviewService = reviewService;
    }
}
