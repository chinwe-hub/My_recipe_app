package org.example.repository;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    boolean save(User user);
}
