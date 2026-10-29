package com.recipebook;

import com.recipebook.model.Ingredient;
import org.testng.annotations.Test;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class IngredientTest {

    @Test
    void storesTrimmedValues(){
        Ingredient ingredient = new Ingredient("flour", 2, "cups");
        assertEquals("flour", ingredient.getName());
        assertEquals(2.0, ingredient.getQuantity(), 0.0001);
        assertEquals("cups", ingredient.getUnit());
    }

    @Test
    void wholeQuantityIsPrintedWithoutDecimals() {
        assertEquals("2 cups flour", new Ingredient("flour", 2.0, "cups").toString());
    }

    @Test
    void fractionalQuantityKeepsDecimals() {
        assertEquals("0.5 tsp salt", new Ingredient("salt", 0.5, "tsp").toString());
    }

    @Test
    void emptyOrNullUnitIsLeftOut() {
        assertEquals("3 eggs", new Ingredient("eggs", 3, "").toString());
        assertEquals("", new Ingredient("eggs", 3, null).getUnit());
        assertEquals("3 eggs", new Ingredient("eggs", 3, null).toString());
    }

    @Test
    void rejectsBlankOrNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("  ", 1, "g"));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(null, 1, "g"));
    }

    @Test
    void rejectsZeroNegativeAndNaNQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("a", 0, "g"));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("a", -1, "g"));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("a", Double.NaN, "g"));
    }
}


