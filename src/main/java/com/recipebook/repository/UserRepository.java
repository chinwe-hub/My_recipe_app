package com.recipebook.repository;

import com.recipebook.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    boolean save(User user);
}
