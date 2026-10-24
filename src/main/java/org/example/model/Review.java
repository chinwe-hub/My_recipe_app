package org.example.model;

public final class Review {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 5;
    public static final int MAX_COMMENT_LENGTH = 200;

    private final String username;
    private final int rating;
    private final String comment;

    public Review(String username, int rating, String comment) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username cannot be null or blank");
        }
        if  (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("rating must be between 1 and 5");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("comment cannot be null or blank");
        }
        if (comment.length() > MAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException("comment length must be less than " + MAX_COMMENT_LENGTH);
        }

        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}