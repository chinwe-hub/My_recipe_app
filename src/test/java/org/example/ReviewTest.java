package org.example;

import org.example.model.Review;
import org.testng.annotations.Test;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;


public class ReviewTest {

    @Test
    void storesTrimmedValues() {
        Review review = new Review("alice", 4, "Tasty");
        assertEquals("alice", review.getUsername());
        assertEquals(4, review.getRating());
        assertEquals("Tasty", review.getComment());
    }

    @Test
    void acceptsBoundaryRatings() {
        assertEquals(1, new Review("alice", 1, "ok").getRating());
        assertEquals(5, new Review("alice", 5, "ok").getRating());
    }

    @Test
    void rejectsRatingsOutsideOneToFive() {
        assertThrows(IllegalArgumentException.class, () -> new Review("alice", 0, "ok"));
        assertThrows(IllegalArgumentException.class, () -> new Review("alice", 6, "ok"));
        assertThrows(IllegalArgumentException.class, () -> new Review("alice", -3, "ok"));
    }

    @Test
    void rejectsBlankOrNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> new Review("", 3, "ok"));
        assertThrows(IllegalArgumentException.class, () -> new Review(null, 3, "ok"));
    }

    @Test
    void rejectsBlankOrNullComment() {
        assertThrows(IllegalArgumentException.class, () -> new Review("alice", 3, "   "));
        assertThrows(IllegalArgumentException.class, () -> new Review("alice", 3, null));
    }

    @Test
    void commentLengthLimitIsTwoHundredCharacters() {
        assertEquals(200, new Review("alice", 3, "x".repeat(200)).getComment().length());
        assertThrows(IllegalArgumentException.class,
                () -> new Review("alice", 3, "x".repeat(201)));
    }
}