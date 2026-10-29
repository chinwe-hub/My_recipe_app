package com.recipebook.repository;

import com.recipebook.model.Review;

import java.util.List;

public interface ReviewRepository {
    void save(Review review);
    List<Review> findByRecipeId(int recipeId);
}
