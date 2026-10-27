package org.example.model;

public class User extends Person{

    private final String username;
    private final String password;

    public User(String username, String password) {
        super(username);
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
