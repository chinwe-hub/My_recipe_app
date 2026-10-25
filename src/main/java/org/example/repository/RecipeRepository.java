package org.example.repository;

import org.example.model.Recipe;

import java.util.List;

public interface RecipeRepository {

    List<Recipe> findAll();

    List<Recipe> findByUsername(String username);
}
