package org.example.controller;

import org.example.model.User;
import org.example.service.AuthenticationService;
import org.example.util.ConsoleInput;

public class AuthenticationController {
    private final ConsoleInput input;
    private final AuthenticationService authenticationService;

    public AuthenticationController(ConsoleInput input, AuthenticationService authenticationService) {
        this.input = input;
        this.authenticationService = authenticationService;
    }

    public User signUp() {
        System.out.println("\n--- Sign Up ---");
        String username = input.readNonBlank("Choose a username: ");
        String password = input.readPassword("Choose a password (at least 8 characters): ");
        try {
            User user = authenticationService.register(username, password);
            System.out.println("Account created. You are now logged in.");
            return user;
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not create account: " + ex.getMessage());
            return null;
        }
    }

    public User logIn() {
        System.out.println("\n--- Log In ---");
        String username = input.readNonBlank("Username: ");
        String password = input.readPassword("Password: ");
        return authenticationService.login(username, password).map(user -> {
            System.out.println("Login successful.");
            return user;
        }).orElseGet(() -> {
            System.out.println("Incorrect username or password.");
            return null;
        });
    }
}
