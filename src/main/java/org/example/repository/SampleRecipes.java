package org.example.repository;

import org.example.model.Ingredient;
import org.example.model.Recipe;

import java.util.List;

final class SampleRecipes {

    private SampleRecipes() {
    }
    static List<Recipe> create(){
        return List.of(
                new Recipe(,
                        "Classic Pancakes", ,
                        "Fluffy breakfast pancakes.", , List.of(ing("all-purpose flour", 1.5, "cups"), ing("milk", 1.25, "cups"),
                                ing("egg", 1, ""), ing("baking powder", 3.5, "tsp"),
                                ing("sugar", 1, "tbsp"), ing("butter, melted", 3, "tbsp")), List.of("Whisk the dry ingredients together.",
                                        "Mix in the milk, egg and melted butter until just combined.",
                                        "Cook 1/4 cup scoops on a hot greased pan until golden on both sides.")),

                new Recipe(,
                        "Spaghetti Carbonara", ,
                        "Creamy Roman pasta without any cream.", , List.of(ing("spaghetti", 400, "g"), ing("pancetta", 150, "g"),
                                ing("egg yolks", 4, ""), ing("Parmesan, grated", 1, "cup"),
                                ing("black pepper", 1, "tsp")), List.of("Boil the spaghetti until al dente.",
                                        "Fry the pancetta until crisp.",
                                        "Toss hot pasta with pancetta, then stir in the yolks and cheese off the heat.")),

                new Recipe(,
                        "Chicken Fried Rice", ,
                        "Quick weeknight dinner using leftover rice.", , List.of(ing("cooked rice", 3, "cups"), ing("chicken breast, diced", 300, "g"),
                                ing("egg", 2, ""), ing("frozen peas and carrots", 1, "cup"),
                                ing("soy sauce", 3, "tbsp"), ing("green onion", 3, "")), List.of("Cook the chicken in a hot wok until browned.",
                                        "Scramble the eggs in the same wok.",
                                        "Add rice, vegetables and soy sauce and stir-fry for 5 minutes.")),

                new Recipe(,
                        "Tomato Basil Soup", ,
                        "Smooth and comforting.", , List.of(ing("canned crushed tomatoes", 800, "g"), ing("onion", 1, ""),
                                ing("garlic cloves", 3, ""), ing("vegetable broth", 2, "cups"),
                                ing("fresh basil", 0.5, "cup"), ing("olive oil", 2, "tbsp")), List.of("Soften the onion and garlic in olive oil.",
                                        "Add tomatoes and broth and simmer for 20 minutes.",
                                        "Add basil and blend until smooth.")),

                new Recipe(,
                        "Vegetable Stir Fry", ,
                        "Colourful and ready in 15 minutes.", , List.of(ing("broccoli florets", 2, "cups"), ing("bell pepper", 1, ""),
                                ing("carrot", 2, ""), ing("soy sauce", 2, "tbsp"),
                                ing("ginger, grated", 1, "tsp"), ing("sesame oil", 1, "tbsp")), List.of("Heat the sesame oil in a wok.",
                                        "Stir-fry the carrot and broccoli for 4 minutes.",
                                        "Add the pepper, ginger and soy sauce and cook 2 more minutes.")),

                new Recipe(,
                        "Greek Salad", ,
                        "Crisp, fresh and no cooking required.", , List.of(ing("tomatoes", 4, ""), ing("cucumber", 1, ""),
                                ing("red onion", 0.5, ""), ing("feta cheese", 200, "g"),
                                ing("Kalamata olives", 0.5, "cup"), ing("olive oil", 3, "tbsp")), List.of("Chop the tomatoes, cucumber and onion.",
                                        "Combine with olives and crumbled feta.",
                                        "Dress with olive oil and oregano."))
        );



    }

    private static Ingredient ing(String name, double quantity, String unit) {
        return new Ingredient(name, quantity, unit);
    }
}
