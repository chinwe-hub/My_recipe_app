package com.recipebook.model;

public abstract class Person {
    private final String username;

    protected Person(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }
        this.username = username.trim();
    }

    public String getUsername() {
        return username;
    }
}
