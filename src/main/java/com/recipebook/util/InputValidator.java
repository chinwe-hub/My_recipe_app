package com.recipebook.util;

public class InputValidator {
    private InputValidator() { }

    public static boolean isNonBlank(String value) {
        return value != null && !value.isBlank();
    }

    // For testing purposes
    public static boolean isRatingValid(int rating) {
        return rating >= 1 && rating <= 5;
    }

    public static boolean isMenuChoiceValid(int choice, int min, int max) {
        return min <= max && choice >= min && choice <= max;
    }

    // For testing purposes
    public static boolean isReviewValid(String review) {
        return review != null && !review.isBlank() && review.trim().length() <= 300;
    }
}
