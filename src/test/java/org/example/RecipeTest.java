
package org.example;

import org.example.model.Ingredient;
import org.example.model.Recipe;
import org.example.model.Review;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    private static Recipe sampleRecipe() {
        return new Recipe(
                1,
                "  Pancakes ",
                "Breakfast",
                " Fluffy ",
                10,
                15,
                List.of(new Ingredient("flour", 1, "cup")),
                List.of("Mix.", "Cook.")
        );
    }

    @Test
    void storesTrimmedValues() {
        Recipe recipe = sampleRecipe();

        assertEquals("Pancakes", recipe.getName());
        assertEquals("Fluffy", recipe.getDescription());
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(2, recipe.getInstructions().size());
    }

    @Test
    void nullDescriptionAndInstructionsBecomeEmpty() {
        Recipe recipe = new Recipe(
                2,
                "Toast",
                "Breakfast",
                null,
                5,
                5,
                List.of(new Ingredient("bread", 1, "")),
                null
        );

        assertEquals("", recipe.getDescription());
        assertTrue(recipe.getInstructions().isEmpty());
    }

    @Test
    void rejectsBlankOrNullName() {
        List<Ingredient> ingredients =
                List.of(new Ingredient("bread", 1, ""));

        assertThrows(
                IllegalArgumentException.class,
                () -> new Recipe(
                        3, " ", "Breakfast", "",
                        5, 5, ingredients, null
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Recipe(
                        4, null, "Breakfast", "",
                        5, 5, ingredients, null
                )
        );
    }

    @Test
    void rejectsMissingIngredients() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Recipe(
                        5, "Toast", "Breakfast", "",
                        5, 5, List.of(), null
                )
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Recipe(
                        6, "Toast", "Breakfast", "",
                        5, 5, null, null
                )
        );
    }

    @Test
    void ingredientAndInstructionListsCannotBeModifiedFromOutside() {
        Recipe recipe = sampleRecipe();

        assertThrows(
                UnsupportedOperationException.class,
                () -> recipe.getIngredients().add(
                        new Ingredient("sugar", 1, "tsp")
                )
        );

        assertThrows(
                UnsupportedOperationException.class,
                () -> recipe.getInstructions().add("Serve.")
        );
    }

    @Test
    void changingTheOriginalListDoesNotChangeTheRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("flour", 1, "cup"));

        Recipe recipe = new Recipe(
                7,
                "Pancakes",
                "Breakfast",
                "",
                10,
                15,
                ingredients,
                null
        );

        ingredients.add(new Ingredient("milk", 1, "cup"));

        assertEquals(1, recipe.getIngredients().size());
    }

    @Test
    void averageRatingIsZeroWithoutReviews() {
        Recipe recipe = sampleRecipe();

        assertEquals(0.0, recipe.getAverageRating(), 0.0001);
        assertTrue(recipe.getReviews().isEmpty());
    }

    @Test
    void averageRatingIsMeanOfAllReviews() {
        Recipe recipe = sampleRecipe();

        recipe.addReview(new Review("alice", 5, "great", 5));
        recipe.addReview(new Review("bob", 4, "good", 4));

        assertEquals(4.5, recipe.getAverageRating(), 0.0001);
        assertEquals(2, recipe.getReviews().size());
    }

    @Test
    void sameUserReviewReplacesTheOldOne() {
        Recipe recipe = sampleRecipe();

        recipe.addReview(new Review("alice", 1, "bad", 3));
        recipe.addReview(new Review("alice", 5, "better now", 2));

        assertEquals(1, recipe.getReviews().size());
        assertEquals(5, recipe.getReviews().get(0).getRating());
        assertEquals(5.0, recipe.getAverageRating(), 0.0001);
    }

    @Test
    void reviewsKeepInsertionOrder() {
        Recipe recipe = sampleRecipe();

        recipe.addReview(new Review("alice", 5, "a", 5));
        recipe.addReview(new Review("bob", 5, "b", 4));

        assertEquals("alice", recipe.getReviews().get(0).getUsername());
        assertEquals("bob", recipe.getReviews().get(1).getUsername());
    }

    @Test
    void rejectsNullReview() {
        assertThrows(
                IllegalArgumentException.class,
                () -> sampleRecipe().addReview(null)
        );
    }
}
