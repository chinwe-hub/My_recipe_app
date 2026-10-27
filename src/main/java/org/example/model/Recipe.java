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

    public static List<Recipe> createSampleRecipes() {
        return List.of(
                new Recipe(
                        "Nigerian Pepper Soup",
                        "A spicy Nigerian soup made with tender goat meat, aromatic spices, and fresh scent leaves.",
                        List.of(
                                new Ingredient("goat meat", 500, "g"),
                                new Ingredient("onion", 1, "whole"),
                                new Ingredient("pepper soup spice", 2, "tbsp"),
                                new Ingredient("ground crayfish", 1, "tbsp"),
                                new Ingredient("scotch bonnet pepper", 1, "whole"),
                                new Ingredient("scent leaves", 1, "cup"),
                                new Ingredient("seasoning cubes", 2, "cubes"),
                                new Ingredient("salt", 1, "tsp"),
                                new Ingredient("water", 4, "cups")
                        ),
                        List.of(
                                "Wash the goat meat thoroughly and place it in a pot.",
                                "Add chopped onion, seasoning cubes, salt, and a little water.",
                                "Cook until the meat begins to soften.",
                                "Add the remaining water, pepper soup spice, ground crayfish, and scotch bonnet pepper.",
                                "Simmer until the meat is tender and the broth is well seasoned.",
                                "Add the scent leaves and cook for another 3 to 5 minutes.",
                                "Taste and adjust the seasoning, then serve hot."
                        )
                )
        );
    }
}
