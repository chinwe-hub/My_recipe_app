package org.example.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

    private final String name;
    private final String description;
    private final List<Ingredient> ingredients;
    private final List<String> instructions;
    private final Map<String, Review> reviewsByUser = new LinkedHashMap<>();

    public Recipe(String name, String description, List<Ingredient> ingredients,
                  List<String> instructions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipe name must not be blank.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("A recipe needs at least one ingredient.");
        }
        this.name = name.trim();
        this.description = description == null ? "" : description.trim();
        this.ingredients = List.copyOf(ingredients);
        this.instructions = instructions == null ? List.of() : List.copyOf(instructions);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public List<Review> getReviews() {
        return List.copyOf(reviewsByUser.values());
    }

    public void addReview(Review review) {
        if (review == null) {
            throw new IllegalArgumentException("Review must not be null.");
        }
        reviewsByUser.put(review.getUsername(), review);
    }

    public double getAverageRating() {
        if (reviewsByUser.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Review review : reviewsByUser.values()) {
            total += review.getRating();
        }
        return (double) total / reviewsByUser.size();
    }
}
