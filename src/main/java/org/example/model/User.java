package org.example.model;

public class User {

    private final String username;
    private final String passwordHash;

    public User(String username, String passwordHash) {

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (passwordHash == null || passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public String getUsername() {
        return username;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
}
