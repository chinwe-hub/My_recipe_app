package org.example.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Recipe {

    private final int id;
    private final String name;
    private final String category;
    private final String description;
    private final int preparationMinutes;
    private final int cookingMinutes;
    private final List<Ingredient> ingredients;
    private final List<String> instructions;
    private final Map<String, Review> reviewsByUser = new LinkedHashMap<>();

    public Recipe(int id, String name, String category, String description, int preparationMinutes, int cookingMinutes, List<Ingredient> ingredients,
                  List<String> instructions) {
        if (id <= 0) throw new IllegalArgumentException("Recipe ID must be positive.");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipe name must not be blank.");
        }
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category is required.");
        if (preparationMinutes < 0 || cookingMinutes < 0) {
            throw new IllegalArgumentException("Cooking times cannot be negative.");
        }
        if (ingredients == null || ingredients.isEmpty()) {
            throw new IllegalArgumentException("A recipe needs at least one ingredient.");
        }
        if (instructions == null || instructions.isEmpty()) throw new IllegalArgumentException("Instructions are required.");
        this.id = id;
        this.name = name.trim();
        this.category = category;
        this.description = description == null ? "" : description.trim();
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.ingredients = List.copyOf(ingredients);
        this.instructions = List.copyOf(instructions);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
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

    public String toExportText() {
        StringBuilder text = new StringBuilder();
        text.append("Recipe: ").append(name).append(System.lineSeparator());
        text.append("Category: ").append(category).append(System.lineSeparator());
        text.append("Preparation time: ").append(preparationMinutes).append(" minutes").append(System.lineSeparator());
        text.append("Cooking time: ").append(cookingMinutes).append(" minutes").append(System.lineSeparator());
        text.append(System.lineSeparator()).append("Ingredients:").append(System.lineSeparator());
        ingredients.forEach(item -> text.append("- ").append(item).append(System.lineSeparator()));
        text.append(System.lineSeparator()).append("Instructions:").append(System.lineSeparator());
        for (int i = 0; i < instructions.size(); i++) {
            text.append(i + 1).append(". ").append(instructions.get(i)).append(System.lineSeparator());
        }
        return text.toString();
    }

    public static List<Recipe> createSampleRecipes() {
        return List.of(
                new Recipe(
                        1,
                        "Jollof Rice",
                        "Main Course",
                        "A popular Nigerian rice dish cooked in a rich tomato and pepper sauce.",
                        15,
                        45,
                        List.of(
                                new Ingredient("long-grain rice", 2, "cups"),
                                new Ingredient("tomatoes, blended", 3, "whole"),
                                new Ingredient("red bell pepper", 1, "whole"),
                                new Ingredient("onion", 1, "whole"),
                                new Ingredient("tomato paste", 2, "tablespoons"),
                                new Ingredient("stock", 2, "cups"),
                                new Ingredient("curry powder", 1, "teaspoon"),
                                new Ingredient("salt", 1, "to taste")
                        ),
                        List.of(
                                "Blend tomatoes, bell pepper, and half the onion.",
                                "Fry chopped onion and tomato paste in a little oil.",
                                "Add blended mixture and seasonings; cook until thickened.",
                                "Add rice and stock, cover, and cook until tender, stirring occasionally."
                        )
                ),

                new Recipe(
                        2,
                        "Pancakes",
                        "Breakfast",
                        "Soft, fluffy pancakes perfect for breakfast.",
                        10,
                        15,
                        List.of(
                                new Ingredient("flour", 1, "cup"),
                                new Ingredient("egg", 1, "whole"),
                                new Ingredient("milk", 0.75, "cup"),
                                new Ingredient("sugar", 1, "tablespoon"),
                                new Ingredient("baking powder", 1, "teaspoon"),
                                new Ingredient("salt", 1, "pinch")
                        ),
                        List.of(
                                "Mix flour, sugar, baking powder, and salt.",
                                "Whisk egg and milk in a separate bowl.",
                                "Combine wet and dry ingredients.",
                                "Cook small portions in a lightly greased pan until golden on both sides."
                        )
                ),

                new Recipe(
                        3,
                        "Vegetable Stir-Fry",
                        "Vegetarian",
                        "A colorful mix of fresh vegetables quickly cooked with garlic and soy sauce.",
                        15,
                        12,
                        List.of(
                                new Ingredient("bell pepper", 1, "whole"),
                                new Ingredient("carrot", 1, "whole"),
                                new Ingredient("broccoli", 1, "cup"),
                                new Ingredient("soy sauce", 2, "tablespoons"),
                                new Ingredient("garlic", 1, "clove"),
                                new Ingredient("oil", 1, "tablespoon")
                        ),
                        List.of(
                                "Wash and slice the vegetables.",
                                "Heat oil in a pan and cook garlic briefly.",
                                "Add vegetables and stir-fry until tender-crisp.",
                                "Add soy sauce, toss, and serve."
                        )
                ),

                new Recipe(
                        4,
                        "Chicken Pasta",
                        "Main Course",
                        "Pasta tossed with tender chicken and a savory tomato sauce.",
                        10,
                        25,
                        List.of(
                                new Ingredient("pasta", 200, "g"),
                                new Ingredient("chicken breast", 1, "whole"),
                                new Ingredient("tomato sauce", 1, "cup"),
                                new Ingredient("garlic", 1, "clove"),
                                new Ingredient("oil", 1, "tablespoon"),
                                new Ingredient("salt and pepper", 1, "to taste")
                        ),
                        List.of(
                                "Cook pasta according to package directions.",
                                "Cut chicken into small pieces and season.",
                                "Cook chicken in oil until fully cooked.",
                                "Add garlic and tomato sauce, simmer, then mix with pasta."
                        )
                ),

                new Recipe(
                        5,
                        "Banana Bread",
                        "Dessert",
                        "A sweet, moist loaf made with ripe bananas.",
                        15,
                        50,
                        List.of(
                                new Ingredient("ripe bananas", 3, "whole"),
                                new Ingredient("flour", 1.5, "cups"),
                                new Ingredient("melted butter", 0.33, "cup"),
                                new Ingredient("sugar", 0.5, "cup"),
                                new Ingredient("egg", 1, "whole"),
                                new Ingredient("baking soda", 1, "teaspoon")
                        ),
                        List.of(
                                "Preheat oven to 175 C (350 F).",
                                "Mash bananas and mix with melted butter.",
                                "Stir in sugar and egg.",
                                "Fold in flour and baking soda.",
                                "Pour into a loaf pan and bake until a toothpick comes out clean."
                        )
                )
        );
    }
}
