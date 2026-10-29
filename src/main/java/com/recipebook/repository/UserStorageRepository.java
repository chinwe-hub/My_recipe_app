package com.recipebook.repository;

import com.recipebook.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserStorageRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null) return Optional.empty();
        return Optional.ofNullable(users.get(username.trim().toLowerCase()));
    }

    @Override
    public boolean save(User user) {
        String key = user.getUsername().toLowerCase();
        if (users.containsKey(key)) return false;
        users.put(key, user);
        return true;
    }
}
