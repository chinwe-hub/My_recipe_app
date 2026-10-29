package com.recipebook;

import com.recipebook.model.User;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    @Test
    void storesUsernameAndPassword(){
        User user = new User("alice", "abc");
        assertEquals("alice", user.getUsername());
        assertEquals("abc", user.getPassword());
    }

    @Test
    void rejectsBlankOrNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> new User(" ", "hash"));
        assertThrows(IllegalArgumentException.class, () -> new User(null, "hash"));
    }

    @Test
    void rejectsBlankOrNullHash() {
        assertThrows(IllegalArgumentException.class, () -> new User("alice", ""));
        assertThrows(IllegalArgumentException.class, () -> new User("alice", null));
    }
}
