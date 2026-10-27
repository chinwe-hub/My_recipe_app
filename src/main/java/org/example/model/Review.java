package org.example.model;

import java.time.LocalDateTime;

public class Review {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;
    public static final int MAX_COMMENT_LENGTH = 200;

    private final String username;
    private final int recipeId;
    private final int rating;
    private final String comment;
    private final LocalDateTime createdAt;

    public Review(String username, int rating, String comment, int recipeId) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (recipeId <= 0) throw new IllegalArgumentException("Recipe ID must be positive.");
        if  (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("rating must be between 1 and 5");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("comment cannot be null or blank");
        }
        if (comment.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("comment length must be less than " + MAX_COMMENT_LENGTH);
        }

        this.username = username;
        this.recipeId = recipeId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}