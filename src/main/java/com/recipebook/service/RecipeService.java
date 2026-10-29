package com.recipebook.service;

import com.recipebook.model.Recipe;

import java.util.List;
import java.util.Optional;

public class RecipeService {

    private final List<Recipe> recipes;

    public RecipeService(List<Recipe> recipes) {
        if (recipes == null) throw new IllegalArgumentException("Recipe collection is required.");
        this.recipes = List.copyOf(recipes);
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> searchByName(String term) {
        if (term == null || term.isBlank()) return List.of();
        String search = term.trim().toLowerCase();
        return recipes.stream()
                .filter(recipe -> recipe.getName().toLowerCase().contains(search))
                .toList();
    }

    public Optional<Recipe> findById(int id) {
        return recipes.stream().filter(recipe -> recipe.getId() == id).findFirst();
    }
}
