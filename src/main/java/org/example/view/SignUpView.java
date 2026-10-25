package org.example.view;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.repository.UserStorageRepository;
import org.example.service.AuthenticationService;
import org.example.util.ConsoleInput;
import java.util.Scanner;

public class SignUpView {
    private final UserRepository userRepository = new UserStorageRepository();
    private final AuthenticationService authenticationService = new AuthenticationService(userRepository);
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleInput input = new ConsoleInput(scanner);
    public User currentUser;

    private void signUp() {
        System.out.println("\n--- Sign Up ---");
        String username = input.readNonBlank("Choose a username: ");
        String password = input.readPassword("Choose a password (at least 8 characters): ");
        try {
            User user = authenticationService.register(username, password);
            currentUser = user;
            System.out.println("Account created. You are now logged in.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not create account: " + ex.getMessage());
        }
    }
}
