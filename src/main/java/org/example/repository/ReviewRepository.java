package org.example.repository;

import org.example.model.Review;

import java.util.List;

public interface ReviewRepository {
    void save(Review review);
    List<Review> findByRecipeId(int recipeId);
}
