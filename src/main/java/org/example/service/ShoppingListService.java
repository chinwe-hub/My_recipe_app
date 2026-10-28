package org.example.service;

import org.example.model.Ingredient;
import org.example.model.Recipe;

import java.util.List;

public class ShoppingListService {
    public List<Ingredient> generate(Recipe recipe) {
        if (recipe == null) throw new IllegalArgumentException("Recipe is required.");
        return List.copyOf(recipe.getIngredients());
    }
}
