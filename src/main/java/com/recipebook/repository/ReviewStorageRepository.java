package com.recipebook.repository;

import com.recipebook.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewStorageRepository implements ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    @Override
    public void save(Review review) {
        reviews.add(review);
    }

    @Override
    public List<Review> findByRecipeId(int recipeId) {
        return reviews.stream().filter(review -> review.getRecipeId() == recipeId).toList();
    }
}
