package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;

import java.util.Optional;

public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        if (userRepository == null) throw new IllegalArgumentException("Dependencies are required.");
        this.userRepository = userRepository;
    }

    public User register(String username, String password) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username cannot be blank.");
        String cleanUsername = username.trim();
        if (!cleanUsername.matches("[A-Za-z0-9_]{3,20}")) {
            throw new IllegalArgumentException("Username must be 3-20 characters and contain only letters, numbers, or underscores.");
        }
        validatePassword(password);
        if (userRepository.findByUsername(cleanUsername).isPresent()) {
            throw new IllegalArgumentException("Username is already registered.");
        }
        User user = new User(cleanUsername, password);
        if (!userRepository.save(user)) throw new IllegalArgumentException("Username is already registered.");
        return user;
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must contain at least 8 characters.");
        }
    }
}
