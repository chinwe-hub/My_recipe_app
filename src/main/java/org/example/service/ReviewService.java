package org.example.service;

import org.example.model.Recipe;
import org.example.model.Review;
import org.example.model.User;
import org.example.repository.ReviewStorageRepository;

import java.util.List;

public class ReviewService {
    private final ReviewStorageRepository reviewStorageRepository;
    private final RecipeService recipeService;

    public ReviewService(ReviewStorageRepository reviewStorageRepository, RecipeService recipeService) {
        if (reviewStorageRepository == null || recipeService == null) throw new IllegalArgumentException("Dependencies are required.");
        this.reviewStorageRepository = reviewStorageRepository;
        this.recipeService = recipeService;
    }

    public Review submitReview(User user, int recipeId, int rating, String comment) {
        if (user == null) throw new IllegalArgumentException("You must be logged in to submit a review.");
        Recipe recipe = recipeService.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe does not exist."));
        Review review = new Review(user.getUsername(), rating, comment, recipe.getId());
        reviewStorageRepository.save(review);
        return review;
    }

    public List<Review> getReviewsForRecipe(int recipeId) {
        if (recipeService.findById(recipeId).isEmpty()) throw new IllegalArgumentException("Recipe does not exist.");
        return reviewStorageRepository.findByRecipeId(recipeId);
    }

    public double getAverageRating(int recipeId) {
        List<Review> reviews = getReviewsForRecipe(recipeId);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
}
