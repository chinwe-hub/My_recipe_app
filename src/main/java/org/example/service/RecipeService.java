package org.example.service;

import org.example.model.Recipe;

import java.util.List;

public class RecipeService {

    private final List<Recipe> recipes;

    public RecipeService(List<Recipe> recipes) {
        if (recipes == null) throw new IllegalArgumentException("Recipe collection is required.");
        this.recipes = List.copyOf(recipes);
    }

}
